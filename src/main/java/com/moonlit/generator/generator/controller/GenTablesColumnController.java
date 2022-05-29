package com.moonlit.generator.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.GenTablesColumn;
import com.moonlit.generator.generator.entity.dto.GenColumnDTO;
import com.moonlit.generator.generator.entity.dto.SaveTablesColumnDTO;
import com.moonlit.generator.generator.service.GenTablesColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/genTablesColumn")
public class GenTablesColumnController {

    @Autowired
    private GenTablesColumnService tablesColumnService;

    /**
     * 条件分页查询
     */
    @GetMapping("/pageList")
    public Result<PageResult<GenTablesColumn>> page(@Validated GenColumnDTO dto) {
        return Result.success(tablesColumnService.pageList(dto));
    }

    /**
     * 刷新表字段
     */
    @PostMapping("/refresh")
    public Result<Boolean> refresh(@RequestBody @Validated SaveTablesColumnDTO saveDTO) {
        return Result.success(tablesColumnService.insertTablesColumn(saveDTO));
    }

    /**
     * 修改保存
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody GenTablesColumn tablesColumn) {
        return Result.success(tablesColumnService.updateTablesColumn(tablesColumn));
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(String ids) {
        return Result.success(tablesColumnService.deleteTablesColumnByIds(ids));
    }

}
