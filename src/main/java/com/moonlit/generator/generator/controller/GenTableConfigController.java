package com.moonlit.generator.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.GenTableConfig;
import com.moonlit.generator.generator.entity.dto.GenTableConfigDTO;
import com.moonlit.generator.generator.entity.vo.GenTableConfigVO;
import com.moonlit.generator.generator.service.GenTableConfigService;
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
@RequestMapping("/genTableConfig")
public class GenTableConfigController {

    @Autowired
    private GenTableConfigService genTableConfigService;

    /**
     * 条件分页查询
     *
     * @param tableConfigDTO 表实体
     * @return 结果集
     */
    @GetMapping("/pageList")
    public Result<PageResult<GenTableConfig>> page(GenTableConfigDTO tableConfigDTO) {
        return Result.success(genTableConfigService.pageList(tableConfigDTO));
    }

    /**
     * 新增保存
     *
     * @param genTableConfig 请求实体
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> addSave(@RequestBody GenTableConfig genTableConfig) {
        return Result.success(genTableConfigService.insertDbDetail(genTableConfig));
    }

    /**
     * 修改保存
     *
     * @param genTableConfig 请求实体
     * @return 结果
     */
    @PostMapping("/update")
    public Result<Boolean> editSave(@RequestBody GenTableConfig genTableConfig) {
        return Result.success(genTableConfigService.updateDbDetail(genTableConfig));
    }

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return 结果
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(String ids) {
        return Result.success(genTableConfigService.deleteDbDetailByIds(ids));
    }

    /**
     * 獲取表配置
     *
     * @return 數據集合
     */
    @GetMapping("/getTableConfig")
    public Result<ArrayList<GenTableConfigVO>> getTableConfig() {
        ArrayList<GenTableConfigVO> list = new ArrayList<>();
        for (GenTableConfig genTableConfig : genTableConfigService.list()) {
            list.add(new GenTableConfigVO(genTableConfig));
        }
        return Result.success(list);
    }
}
