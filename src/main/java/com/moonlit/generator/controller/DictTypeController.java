package com.moonlit.generator.controller;

import com.moonlit.generator.common.response.DictVO;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.service.IDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Api(value = "数据字典配置", tags = {"数据字典配置"})
public class DictTypeController {

    @Autowired
    private IDictTypeService sysDictTypeService;

    /**
     * 根据CODE查询字典所有值
     *
     * @param code 编号
     * @return 结果集
     */
    @GetMapping("/dropDown")
    @ApiOperation("根据code查询对应的字段")
    public Result<List<DictVO>> dropDown(String code) {
        return Result.success(sysDictTypeService.dropDown(code));
    }

}