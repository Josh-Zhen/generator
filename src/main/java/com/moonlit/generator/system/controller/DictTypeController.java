package com.moonlit.generator.system.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.system.entity.DictType;
import com.moonlit.generator.system.entity.vo.DictVO;
import com.moonlit.generator.system.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/dict")
public class DictTypeController {

    @Autowired
    private DictTypeService dictTypeService;

    /**
     * 根据CODE查询字典所有值
     *
     * @param code 编号
     * @return 结果集
     */
    @GetMapping("/dropDown")
    public Result<List<DictVO>> dropDown(String code) {
        return Result.success(dictTypeService.dropDown(code));
    }

    /**
     * 分页查询字典管理
     */
    @GetMapping("/pageList")
    public Result<PageResult<DictType>> page(DictType dictType) {
        return Result.success(dictTypeService.pageList(dictType));
    }

    /**
     * 新增保存字典管理
     */
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody DictType dictType) {
        return Result.success(dictTypeService.insertDictType(dictType));
    }

    /**
     * 修改字典管理
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody DictType dictType) {
        return Result.success(dictTypeService.updateDictType(dictType));
    }

    /**
     * 删除字典管理
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(Long id) {
        return Result.success(dictTypeService.deleteDictTypeById(id));
    }

}