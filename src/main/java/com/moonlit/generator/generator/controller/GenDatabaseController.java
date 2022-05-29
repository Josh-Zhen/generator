package com.moonlit.generator.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.GenDatabase;
import com.moonlit.generator.generator.entity.dto.GenDatabaseDTO;
import com.moonlit.generator.generator.service.GenDatabaseService;
import com.moonlit.generator.system.entity.vo.DictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result<PageResult<GenDatabase>> page(GenDatabaseDTO genDatabaseDTO) {
        return Result.success(genDatabaseService.pageList(genDatabaseDTO));
    }

    /**
     * 新增连接库
     */
    @PostMapping("/save")
    public Result<Boolean> addSave(@RequestBody GenDatabase genDatabase) {
        return Result.success(genDatabaseService.insertDatabase(genDatabase));
    }

    /**
     * 修改连接库
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody GenDatabase genDatabase) {
        return Result.success(genDatabaseService.updateDatabase(genDatabase));
    }

    /**
     * 批量删除连接库
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(String ids) {
        return Result.success(genDatabaseService.deleteDatabaseByIds(ids));
    }

    /**
     * 獲取數據庫名
     *
     * @return 结果集
     */
    @GetMapping("/dropDown")
    public Result<List<DictVO>> dropDown() {
        return Result.success(genDatabaseService.dropDown());
    }
}
