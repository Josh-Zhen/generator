package com.moonlit.generator.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.GenDatabase;
import com.moonlit.generator.generator.entity.dto.GenDatabaseDTO;
import com.moonlit.generator.generator.service.GenDatabaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 数据库配置控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:30
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/genDatabase")
@Api(value = "数据库配置", tags = {"数据库配置"})
public class GenDatabaseController {

    @Autowired
    private GenDatabaseService genDatabaseService;

    /**
     * 条件分页查询
     *
     * @param genDatabaseDTO 表实体
     * @return 结果集
     */
    @GetMapping("/pageList")
    @ApiOperation("分页查询数据库明细")
    public Result<PageResult<GenDatabase>> page(GenDatabaseDTO genDatabaseDTO) {
        return Result.success(genDatabaseService.pageList(genDatabaseDTO));
    }

    /**
     * 新增连接库
     */
    @PostMapping("/save")
    @ApiOperation("新增保存连接库")
    public Result<Boolean> addSave(@RequestBody GenDatabase genDatabase) {
        return Result.success(genDatabaseService.insertDatabase(genDatabase));
    }

    /**
     * 修改连接库
     */
    @PostMapping("/update")
    @ApiOperation("修改保存连接库")
    public Result<Boolean> editSave(@RequestBody GenDatabase genDatabase) {
        return Result.success(genDatabaseService.updateDatabase(genDatabase));
    }

    /**
     * 批量删除连接库
     */
    @PostMapping("/delete")
    @ApiOperation("批量删除连接库")
    public Result<Boolean> delete(String ids) {
        return Result.success(genDatabaseService.deleteDatabaseByIds(ids));
    }

}
