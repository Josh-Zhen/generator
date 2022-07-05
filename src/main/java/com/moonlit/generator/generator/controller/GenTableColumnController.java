package com.moonlit.generator.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.GenTableColumn;
import com.moonlit.generator.generator.entity.dto.GenColumnDTO;
import com.moonlit.generator.generator.entity.dto.SaveTableColumnDTO;
import com.moonlit.generator.generator.service.GenTableColumnService;
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
@RequestMapping("/genTableColumn")
public class GenTableColumnController {

    @Autowired
    private GenTableColumnService tableColumnService;

    /**
     * 条件分页查询
     */
    @GetMapping("/pageList")
    public Result<PageResult<GenTableColumn>> page(@Validated GenColumnDTO dto) {
        return Result.success(tableColumnService.pageList(dto));
    }

    /**
     * 刷新表字段
     */
    @PostMapping("/refresh")
    public Result<Boolean> refresh(@RequestBody @Validated SaveTableColumnDTO saveDTO) {
        return Result.success(tableColumnService.insertTableColumn(saveDTO));
    }

    /**
     * 修改保存
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody GenTableColumn tableColumn) {
        return Result.success(tableColumnService.updateTableColumn(tableColumn));
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(String ids) {
        return Result.success(tableColumnService.deleteTableColumnByIds(ids));
    }

}
