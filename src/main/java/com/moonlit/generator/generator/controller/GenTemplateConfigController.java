package com.moonlit.generator.generator.controller;

import cn.hutool.core.io.IoUtil;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.constants.error.TemplateErrorCode;
import com.moonlit.generator.generator.entity.GenTemplateConfig;
import com.moonlit.generator.generator.entity.dto.DeleteTemplateConfigDTO;
import com.moonlit.generator.generator.entity.dto.ExportDTO;
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
    public Result<Boolean> delete(DeleteTemplateConfigDTO dto) {
        return Result.success(templateConfigService.deleteTemplateConfigByIds(dto));
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
     * 批量導出代碼
     *
     * @param response  http響應
     * @param exportDTO 表id集合
     */
    @PostMapping("/batchExport")
    public void batchExport(HttpServletResponse response, @RequestBody ExportDTO exportDTO) {
        // 獲取模板
        List<GenTemplateConfig> templates = templateCollectionService.getTemplateByCollectionId(exportDTO.getCollectionId());

        // 獲取導出的數據字節
        byte[] data = templateConfigService.exportTemplate(exportDTO.getTableIds(), exportDTO.getTableConfigId(), templates);

        try {
            // 構建zip文件
            response.reset();
            response.setHeader("Access-Control-Expose-Headers", "content-disposition");
            response.setHeader("content-disposition", "attachment; filename=" + exportDTO.getFileName() + ".zip");
            response.setHeader("Content-Length", String.valueOf(data.length));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            IoUtil.write(response.getOutputStream(), false, data);
        } catch (IOException e) {
            throw new BusinessException(TemplateErrorCode.EXPORT_CODE_EXCEPTION);
        }
    }

}
