package com.moonlit.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.entity.GenDb;
import com.moonlit.generator.service.GenDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 连接库控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:30
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/genDb")
public class GenDbController {

    @Autowired
    private GenDbService genDbService;

    /**
     * 分页查询数据库明细
     */
    @GetMapping("/pageList")
    public Result<PageResult<GenDb>> page(GenDb genDb) {
        return Result.success(genDbService.pageList(genDb));
    }

    /**
     * 新增保存数据库明细
     */
    @PostMapping("/save")
    public Result<Boolean> addSave(@RequestBody GenDb genDb) {
        return Result.success(genDbService.insertDbDetail(genDb));
    }

    /**
     * 修改保存数据库明细
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody GenDb genDb) {
        return Result.success(genDbService.updateDbDetail(genDb));
    }

    /**
     * 删除数据库明细
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(String ids) {
        return Result.success(genDbService.deleteDbDetailByIds(ids));
    }
}
