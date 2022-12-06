package com.moonlit.generator.system.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.system.entity.DictData;
import com.moonlit.generator.system.service.DictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 数据字典值配置 控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/11/18 9:08
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/dict/data")
public class DictDataController {

    @Autowired
    private DictDataService dictDataService;

    /**
     * 分页查询字典管理(以父id查询)
     *
     * @param dictData 查询条件
     * @return 集合列
     */
    @GetMapping("/pageList")
    public Result<PageResult<DictData>> page(DictData dictData) {
        return Result.success(dictDataService.pageList(dictData));
    }

    /**
     * 新增字典管理
     *
     * @param dictData 实体
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody DictData dictData) {
        return Result.success(dictDataService.insertDictData(dictData));
    }

    /**
     * 修改字典管理
     *
     * @param dictData 实体
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody DictData dictData) {
        return Result.success(dictDataService.updateDictData(dictData));
    }

    /**
     * 删除字典管理
     *
     * @param id 主键
     * @return 结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(Long id) {
        return Result.success(dictDataService.removeById(id));
    }

}
