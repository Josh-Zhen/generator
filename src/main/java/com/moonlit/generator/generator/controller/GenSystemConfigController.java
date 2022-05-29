package com.moonlit.generator.generator.controller;

import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.generator.entity.dto.SystemConfigDTO;
import com.moonlit.generator.generator.service.GenSystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class GenSystemConfigController {

    @Autowired
    private GenSystemConfigService systemConfigService;

    /**
     * 獲取系統配置
     *
     * @return 系統配置
     */
    @GetMapping("/getSystemConfig")
    public Result<SystemConfigDTO> getSystemConfig() {
        return Result.success(systemConfigService.getSystemConfig());
    }

    /**
     * 刷新密鑰
     */
    @GetMapping("/refreshKey")
    public Result<String> refreshKey() {
        return Result.success(systemConfigService.refreshKey());
    }

    /**
     * 設置密鑰
     *
     * @param dto 數據實體
     * @return 結果
     */
    @PostMapping("/setSalt")
    public Result<Boolean> setSalt(@RequestBody SystemConfigDTO dto) {
        return Result.success(systemConfigService.updateSystemConfig(dto));
    }
}
