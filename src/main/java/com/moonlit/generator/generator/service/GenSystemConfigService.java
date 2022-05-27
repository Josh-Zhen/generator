package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.generator.entity.GenSystemConfig;

/**
 * 系統配置业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 9:06
 * @email by.Moonlit@hotmail.com
 */
public interface GenSystemConfigService extends IService<GenSystemConfig> {

    /**
     * 獲取RSA密鑰
     * TODO 該密鑰應該交給用戶存儲或者讓用戶選擇是否交給平臺
     *
     * @return 密鑰
     */
    String getRsaKey();


}
