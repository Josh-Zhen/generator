package com.moonlit.generator.generator.controller;

import cn.hutool.core.io.IoUtil;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.constants.error.TemplateErrorCode;
import com.moonlit.generator.generator.entity.GenTemplateConfig;
import com.moonlit.generator.generator.entity.dto.GenTemplateConfigDTO;
import com.moonlit.generator.generator.entity.dto.PreviewTemplateDTO;
import com.moonlit.generator.generator.service.GenTemplateCollectionService;
import com.moonlit.generator.generator.service.GenTemplateConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板配置控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 31/5/2022 16:41
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/genTemplateConfig")
public class GenTemplateConfigController {

    @Autowired
    private GenTemplateConfigService templateConfigService;
    @Autowired
    private GenTemplateCollectionService templateCollectionService;

    /**
     * 条件分页查询
     */
    @GetMapping("/pageList")
    public Result<PageResult<GenTemplateConfig>> page(GenTemplateConfigDTO templateConfigDTO) {
        return Result.success(templateConfigService.pageList(templateConfigDTO));
    }

    /**
     * 新增保存
     */
    @PostMapping("/save")
    public Result<Boolean> addSave(@RequestBody GenTemplateConfig templateConfig) {
        return Result.success(templateConfigService.insertTemplateConfig(templateConfig));
    }

    /**
     * 修改保存
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody GenTemplateConfig templateConfig) {
        return Result.success(templateConfigService.updateTemplateConfig(templateConfig));
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(String ids) {
        return Result.success(templateConfigService.deleteTemplateConfigByIds(ids));
    }

    /**
     * 預覽代碼
     *
     * @param tableId 表id
     * @return 結果
     */
    @GetMapping("/preview/{tableId}")
    public Result<ArrayList<PreviewTemplateDTO>> preview(@PathVariable Long tableId) {
        return Result.success(templateConfigService.previewTemplateByTableId(tableId));
    }

    /**
     * 導出代碼
     *
     * @param response      http響應
     * @param tableName     表名
     * @param tableId       表id
     * @param tableConfigId 表配置id
     * @param collectionId  模板組id
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response, @RequestParam String tableName, @RequestParam Long tableId, @RequestParam Long tableConfigId, @RequestParam Long collectionId) {
        // 模板
        List<GenTemplateConfig> templates = templateCollectionService.getTemplateByCollectionId(collectionId);
        byte[] data = templateConfigService.exportTemplate(tableId, tableConfigId, templates);
        try {
            // 構建zip文件
            response.reset();
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Expose-Headers", "content-disposition");
            response.setHeader("content-disposition", "attachment; filename=" + tableName + ".zip");
            response.setHeader("Content-Length", String.valueOf(data.length));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            IoUtil.write(response.getOutputStream(), false, data);
        } catch (IOException e) {
            throw new BusinessException(TemplateErrorCode.EXPORT_CODE_EXCEPTION);
        }
    }

    /**
     * 批量導出代碼
     *
     * @param tableIds 表id集合
     */
    public void batchExport(HttpServletResponse response, @PathVariable String tableIds) {

    }


}
