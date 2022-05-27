package com.moonlit.generator.generator.controller;

import cn.hutool.core.util.RandomUtil;
import com.moonlit.generator.common.encrypt.RsaUtils;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.GenSystemConfig;
import com.moonlit.generator.generator.entity.dto.SetSaltDTO;
import com.moonlit.generator.generator.service.GenDatabaseService;
import com.moonlit.generator.generator.service.GenSystemConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * 系統配置控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/genSystemConfig")
@Api(value = "系統配置", tags = {"系統配置"})
public class GenSystemConfigController {

    @Autowired
    private GenSystemConfigService genSystemConfigService;

    @Autowired
    private GenDatabaseService genDatabaseService;

    /**
     * 刷新密鑰
     */
    @ApiOperation("刷新密鑰")
    @GetMapping("/refreshKey")
    public Result<Boolean> refreshKey() {
        GenSystemConfig systemConfig = genSystemConfigService.getById(1);
        // 获取新密钥
        HashMap<String, String> keys = RsaUtils.genKeyPair();
        systemConfig.setPublicKey(keys.get("publicKey"));
        String privateKey = keys.get("privateKey");
        systemConfig.setPrivateKey(privateKey);
        String salt = RandomUtil.randomString(10);
        systemConfig.setSalt(RsaUtils.privateEncrypt(salt, privateKey));
        systemConfig.setUpdateDate(LocalDateTime.now());

        // 更新數據庫連接的用戶名與密碼
        genDatabaseService.updateDatabasesInData(salt);
        return Result.success(genSystemConfigService.updateById(systemConfig));
    }

    /**
     * 設置密鑰
     *
     * @param salt 密鑰
     * @return 密鑰
     */
    @PostMapping("/setSalt")
    public Result<String> setSalt(@RequestBody SetSaltDTO salt) {

        return Result.success();
    }
}
