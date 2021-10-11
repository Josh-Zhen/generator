package com.moonlit.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.entity.DbDetail;
import com.moonlit.generator.service.DbDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 数据库明细控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:30
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/dbDetail")
public class DbDetailController {

    @Autowired
    private DbDetailService dbDetailService;

    /**
     * 分页查询数据库明细
     */
    @GetMapping("/pageList")
    public Result<PageResult<DbDetail>> page(DbDetail dbDetail) {
        return Result.success(dbDetailService.pageList(dbDetail));
    }

    /**
     * 新增保存数据库明细
     */
    @PostMapping("/save")
    public Result<Boolean> addSave(@RequestBody DbDetail dbDetail) {
        return Result.success(dbDetailService.insertDbDetail(dbDetail));
    }

    /**
     * 修改保存数据库明细
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody DbDetail dbDetail) {
        return Result.success(dbDetailService.updateDbDetail(dbDetail));
    }

    /**
     * 删除数据库明细
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(String ids) {
        return Result.success(dbDetailService.deleteDbDetailByIds(ids));
    }
}
