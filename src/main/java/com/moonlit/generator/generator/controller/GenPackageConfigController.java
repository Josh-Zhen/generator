package com.moonlit.generator.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.GenPackageConfig;
import com.moonlit.generator.generator.service.GenPackageConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 包配置控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/genPackageConfig")
@Api(value = "包配置", tags = {"包配置"})
public class GenPackageConfigController {

    @Autowired
    private GenPackageConfigService genPackageConfigService;

    /**
     * 条件分页查询
     *
     * @param genPackageConfig 表实体
     * @return 结果集
     */
    @GetMapping("/pageList")
    @ApiOperation("分页查询")
    public Result<PageResult<GenPackageConfig>> page(GenPackageConfig genPackageConfig) {
        return Result.success(genPackageConfigService.pageList(genPackageConfig));
    }

    /**
     * 新增保存
     *
     * @param genPackageConfig 请求实体
     * @return 结果
     */
    @PostMapping("/save")
    @ApiOperation("新增保存")
    public Result<Boolean> addSave(@RequestBody GenPackageConfig genPackageConfig) {
        return Result.success(genPackageConfigService.insertDbDetail(genPackageConfig));
    }

    /**
     * 修改保存
     *
     * @param genPackageConfig 请求实体
     * @return 结果
     */
    @PostMapping("/update")
    @ApiOperation("修改保存")
    public Result<Boolean> editSave(@RequestBody GenPackageConfig genPackageConfig) {
        return Result.success(genPackageConfigService.updateDbDetail(genPackageConfig));
    }

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @PostMapping("/delete")
    @ApiOperation("批量删除")
    public Result<Boolean> delete(String ids) {
        return Result.success(genPackageConfigService.deleteDbDetailByIds(ids));
    }

}
