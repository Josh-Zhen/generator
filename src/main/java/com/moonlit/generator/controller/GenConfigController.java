package com.moonlit.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.entity.GenConfig;
import com.moonlit.generator.service.GenConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 作者相关配置值控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/genConfig")
@Api(value = "作者相关配置", tags = {"作者相关配置"})
public class GenConfigController {

    @Autowired
    private GenConfigService genConfigService;

    /**
     * 条件分页查询
     *
     * @param genConfig 表实体
     * @return 结果集
     */
    @GetMapping("/pageList")
    @ApiOperation("分页查询")
    public Result<PageResult<GenConfig>> page(GenConfig genConfig) {
        return Result.success(genConfigService.pageList(genConfig));
    }

    /**
     * 新增保存
     *
     * @param genConfig 请求实体
     * @return 结果
     */
    @PostMapping("/save")
    @ApiOperation("新增保存")
    public Result<Boolean> addSave(@RequestBody GenConfig genConfig) {
        return Result.success(genConfigService.insertDbDetail(genConfig));
    }

    /**
     * 修改保存
     *
     * @param genConfig 请求实体
     * @return 结果
     */
    @PostMapping("/update")
    @ApiOperation("修改保存")
    public Result<Boolean> editSave(@RequestBody GenConfig genConfig) {
        return Result.success(genConfigService.updateDbDetail(genConfig));
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
        return Result.success(genConfigService.deleteDbDetailByIds(ids));
    }
}
