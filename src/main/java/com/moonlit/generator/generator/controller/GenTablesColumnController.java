package com.moonlit.generator.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.GenTablesColumn;
import com.moonlit.generator.generator.entity.dto.GenColumnDTO;
import com.moonlit.generator.generator.entity.dto.SaveTablesColumnDTO;
import com.moonlit.generator.generator.service.GenTablesColumnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 數據表字段詳情控制層
 *
 * @author Joshua
 * @version 1.0
 * @date 22/5/2022 15:26
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/genTables")
@Api(value = "數據表字段詳情", tags = {"數據表字段詳情"})
public class GenTablesColumnController {

    @Autowired
    private GenTablesColumnService tablesColumnService;

    /**
     * 条件分页查询
     */
    @GetMapping("/pageList")
    @ApiOperation("条件分页查询")
    public Result<PageResult<GenTablesColumn>> page(GenColumnDTO dto) {
        return Result.success(tablesColumnService.pageList(dto));
    }

    /**
     * 新增保存
     */
    @PostMapping("/save")
    @ApiOperation("新增保存")
    public Result<Boolean> addSave(@RequestBody SaveTablesColumnDTO saveDTO) {
        return Result.success(tablesColumnService.insertTablesColumn(saveDTO));
    }

    /**
     * 修改保存
     */
    @PostMapping("/update")
    @ApiOperation("修改保存")
    public Result<Boolean> editSave(@RequestBody GenTablesColumn tablesColumn) {
        return Result.success(tablesColumnService.updateTablesColumn(tablesColumn));
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    @ApiOperation("批量删除")
    public Result<Boolean> delete(String ids) {
        return Result.success(tablesColumnService.deleteTablesColumnByIds(ids));
    }

}
