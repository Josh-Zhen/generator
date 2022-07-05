package com.moonlit.generator.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.GenTable;
import com.moonlit.generator.generator.entity.dto.GenTableDTO;
import com.moonlit.generator.generator.entity.dto.SaveGenTableDTO;
import com.moonlit.generator.generator.entity.vo.DatabaseTablesVO;
import com.moonlit.generator.generator.service.GenTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 數據表詳情控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 8:43
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/genTable")
public class GenTableController {

    @Autowired
    private GenTableService genTableService;

    /**
     * 条件分页查询
     */
    @GetMapping("/pageList")
    public Result<PageResult<GenTable>> page(GenTableDTO genTableDTO) {
        return Result.success(genTableService.pageList(genTableDTO));
    }

    /**
     * 新增保存
     */
    @PostMapping("/save")
    public Result<Boolean> addSave(@RequestBody @Validated SaveGenTableDTO genTable) {
        return Result.success(genTableService.insertTable(genTable));
    }

    /**
     * 修改保存
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody GenTable genTable) {
        return Result.success(genTableService.updateTable(genTable));
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(String ids) {
        return Result.success(genTableService.deleteTableByIds(ids));
    }

    /**
     * 根據庫id獲取庫内未添加的表
     *
     * @param databaseId 庫id
     * @return 表數據
     */
    @GetMapping("/list")
    public Result<List<DatabaseTablesVO>> list(Long databaseId) {
        return Result.success(genTableService.list(databaseId));
    }

}
