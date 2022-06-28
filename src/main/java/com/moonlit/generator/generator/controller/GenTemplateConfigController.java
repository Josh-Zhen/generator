package com.moonlit.generator.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.GenTemplateConfig;
import com.moonlit.generator.generator.entity.dto.GenTemplateConfigDTO;
import com.moonlit.generator.generator.service.GenTemplateConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
