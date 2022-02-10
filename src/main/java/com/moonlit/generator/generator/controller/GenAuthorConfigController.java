package com.moonlit.generator.generator.controller;

import com.moonlit.generator.common.encrypt.RsaUtils;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.GenAuthorConfig;
import com.moonlit.generator.generator.entity.dto.GenAuthorConfigDTO;
import com.moonlit.generator.generator.service.GenAuthorConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 作者配置控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/genAuthorConfig")
@Api(value = "作者配置", tags = {"作者配置"})
public class GenAuthorConfigController {

    @Autowired
    private GenAuthorConfigService genAuthorConfigService;

    /**
     * 条件分页查询
     *
     * @param genAuthorConfigDTO 表实体
     * @return 结果集
     */
    @GetMapping("/pageList")
    @ApiOperation("分页查询")
    public Result<PageResult<GenAuthorConfig>> page(GenAuthorConfigDTO genAuthorConfigDTO) {
        return Result.success(genAuthorConfigService.pageList(genAuthorConfigDTO));
    }

    /**
     * 新增保存
     *
     * @param genAuthorConfig 请求实体
     * @return 结果
     */
    @PostMapping("/save")
    @ApiOperation("新增保存")
    public Result<Boolean> addSave(@RequestBody GenAuthorConfig genAuthorConfig) {
        return Result.success(genAuthorConfigService.insertDbDetail(genAuthorConfig));
    }

    /**
     * 修改保存
     *
     * @param genAuthorConfig 请求实体
     * @return 结果
     */
    @PostMapping("/update")
    @ApiOperation("修改保存")
    public Result<Boolean> editSave(@RequestBody GenAuthorConfig genAuthorConfig) {
        return Result.success(genAuthorConfigService.updateDbDetail(genAuthorConfig));
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
        return Result.success(genAuthorConfigService.deleteDbDetailByIds(ids));
    }

    /**
     * 生成密鑰
     *
     * @return 密鑰對
     */
    @ApiOperation("生成密鑰")
    @GetMapping("/generateKeys")
    public Result<HashMap<String, String>> generateKeys() {
        return Result.success(RsaUtils.genKeyPair());
    }

}
