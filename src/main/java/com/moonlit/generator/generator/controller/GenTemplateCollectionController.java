package com.moonlit.generator.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.GenTemplateCollection;
import com.moonlit.generator.generator.entity.dto.GenTemplateCollectionDTO;
import com.moonlit.generator.generator.entity.vo.CollectionVO;
import com.moonlit.generator.generator.service.GenTemplateCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * 模板组控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 28/6/2022 16:15
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/genTemplateCollection")
public class GenTemplateCollectionController {

    @Autowired
    private GenTemplateCollectionService templateCollectionService;

    /**
     * 条件分页查询
     */
    @GetMapping("/pageList")
    public Result<PageResult<GenTemplateCollection>> page(GenTemplateCollectionDTO collectionDTO) {
        return Result.success(templateCollectionService.pageList(collectionDTO));
    }

    /**
     * 新增保存
     */
    @PostMapping("/save")
    public Result<Boolean> addSave(@RequestBody GenTemplateCollection templateCollection) {
        return Result.success(templateCollectionService.insertTemplateCollection(templateCollection));
    }

    /**
     * 修改保存
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody GenTemplateCollection templateCollection) {
        return Result.success(templateCollectionService.updateTemplateCollection(templateCollection));
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(String ids) {
        return Result.success(templateCollectionService.deleteTemplateCollectionByIds(ids));
    }

    /**
     * 获取组名称
     *
     * @return 结果集合
     */
    @GetMapping("/getCollectionName")
    public Result<ArrayList<CollectionVO>> getCollectionName() {
        return Result.success(templateCollectionService.getCollectionName());
    }

}
