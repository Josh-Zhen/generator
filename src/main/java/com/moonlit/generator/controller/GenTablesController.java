package com.moonlit.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.entity.GenTables;
import com.moonlit.generator.service.GenTablesColumnService;
import com.moonlit.generator.service.GenTablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 库表生成_表控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 8:43
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/genTables")
public class GenTablesController {

    @Autowired
    private GenTablesService genTablesService;

    @Autowired
    private GenTablesColumnService genTablesColumnService;

    /**
     * 条件分页查询
     */
    @GetMapping("/pageList")
    public Result<PageResult<GenTables>> page(GenTables genTables) {
        return Result.success(genTablesService.pageList(genTables));
    }

    /**
     * 新增保存
     */
    @PostMapping("/save")
    public Result<Boolean> addSave(@RequestBody GenTables genTables) {
        return Result.success(genTablesService.insertTables(genTables));
    }

    /**
     * 修改保存
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody GenTables genTables) {
        return Result.success(genTablesService.updateTables(genTables));
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(String ids) {
        return Result.success(genTablesService.deleteTablesByIds(ids));
    }
}
