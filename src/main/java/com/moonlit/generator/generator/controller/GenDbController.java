package com.moonlit.generator.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.GenConfig;
import com.moonlit.generator.generator.entity.GenDb;
import com.moonlit.generator.generator.entity.GenTables;
import com.moonlit.generator.generator.entity.vo.TestVO;
import com.moonlit.generator.generator.service.GenDbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 连接数据库控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:30
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/genDb")
@Api(value = "数据库", tags = {"数据库"})
public class GenDbController {

    @Autowired
    private GenDbService genDbService;

    /**
     * 条件分页查询
     *
     * @param genDb 表实体
     * @return 结果集
     */
    @GetMapping("/pageList")
    @ApiOperation("分页查询数据库明细")
    public Result<PageResult<GenDb>> page(GenDb genDb) {
        return Result.success(genDbService.pageList(genDb));
    }

    /**
     * 新增连接库
     */
    @PostMapping("/save")
    @ApiOperation("新增保存连接库")
    public Result<Boolean> addSave(@RequestBody GenDb genDb) {
        return Result.success(genDbService.insertDbDetail(genDb));
    }

    /**
     * 修改连接库
     */
    @PostMapping("/update")
    @ApiOperation("修改保存连接库")
    public Result<Boolean> editSave(@RequestBody GenDb genDb) {
        return Result.success(genDbService.updateDbDetail(genDb));
    }

    /**
     * 批量删除连接库
     */
    @PostMapping("/delete")
    @ApiOperation("批量删除连接库")
    public Result<Boolean> delete(String ids) {
        return Result.success(genDbService.deleteDbDetailByIds(ids));
    }
    
}
