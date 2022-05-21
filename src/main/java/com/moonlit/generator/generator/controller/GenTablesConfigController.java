package com.moonlit.generator.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.GenTablesConfig;
import com.moonlit.generator.generator.entity.vo.GenTablesConfigVO;
import com.moonlit.generator.generator.service.GenTablesConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * 數據表控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/genTablesConfig")
@Api(value = "數據表", tags = {"數據表"})
public class GenTablesConfigController {

    @Autowired
    private GenTablesConfigService genTablesConfigService;

    /**
     * 条件分页查询
     *
     * @param genTablesConfig 表实体
     * @return 结果集
     */
    @GetMapping("/pageList")
    @ApiOperation("分页查询")
    public Result<PageResult<GenTablesConfig>> page(GenTablesConfig genTablesConfig) {
        return Result.success(genTablesConfigService.pageList(genTablesConfig));
    }

    /**
     * 新增保存
     *
     * @param genTablesConfig 请求实体
     * @return 结果
     */
    @PostMapping("/save")
    @ApiOperation("新增保存")
    public Result<Boolean> addSave(@RequestBody GenTablesConfig genTablesConfig) {
        return Result.success(genTablesConfigService.insertDbDetail(genTablesConfig));
    }

    /**
     * 修改保存
     *
     * @param genTablesConfig 请求实体
     * @return 结果
     */
    @PostMapping("/update")
    @ApiOperation("修改保存")
    public Result<Boolean> editSave(@RequestBody GenTablesConfig genTablesConfig) {
        return Result.success(genTablesConfigService.updateDbDetail(genTablesConfig));
    }

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @PostMapping("/delete")
    @ApiOperation("批量删除")
    public Result<Boolean> delete(String ids) {
        return Result.success(genTablesConfigService.deleteDbDetailByIds(ids));
    }

    /**
     * 獲取表配置
     *
     * @return 數據集合
     */
    @GetMapping("/getTablesConfig")
    @ApiOperation("獲取表配置")
    public Result<ArrayList<GenTablesConfigVO>> getTablesConfig() {
        ArrayList<GenTablesConfigVO> list = new ArrayList<>();
        for (GenTablesConfig genTablesConfig : genTablesConfigService.list()) {
            list.add(new GenTablesConfigVO(genTablesConfig));
        }
        return Result.success(list);
    }
}
