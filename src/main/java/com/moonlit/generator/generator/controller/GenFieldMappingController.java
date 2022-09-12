package com.moonlit.generator.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.GenFieldMapping;
import com.moonlit.generator.generator.service.GenFieldMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 键值映射控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 12/09/2022 23:11
 */
@RestController
@RequestMapping("/fieldMapping")
public class GenFieldMappingController {

    @Autowired
    private GenFieldMappingService fieldMappingService;

    /**
     * 分页查询键值映射
     *
     * @param fieldMapping 键值映射实体
     * @return 键值映射集合
     */
    @GetMapping("/pageList")
    public Result<PageResult<GenFieldMapping>> page(GenFieldMapping fieldMapping) {
        return Result.success(fieldMappingService.pageList(fieldMapping));
    }

    /**
     * 查询键值映射列表
     *
     * @param fieldMapping 键值映射实体
     * @return 键值映射集合
     */
    @GetMapping("/list")
    public Result<List<GenFieldMapping>> list(GenFieldMapping fieldMapping) {
        return Result.success(fieldMappingService.selectFieldMappingList(fieldMapping));
    }

    /**
     * 新增键值映射
     *
     * @param fieldMapping 键值映射实体
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> addSave(@RequestBody GenFieldMapping fieldMapping) {
        return Result.success(fieldMappingService.insertFieldMapping(fieldMapping));
    }

    /**
     * 修改键值映射
     *
     * @param fieldMapping 键值映射实体
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody GenFieldMapping fieldMapping) {
        return Result.success(fieldMappingService.updateFieldMapping(fieldMapping));
    }

    /**
     * 批量删除键值映射
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(String ids) {
        return Result.success(fieldMappingService.deleteFieldMappingByIds(ids));
    }

}
