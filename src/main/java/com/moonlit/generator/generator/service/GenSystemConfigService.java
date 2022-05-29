package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.generator.entity.GenSystemConfig;
import com.moonlit.generator.generator.entity.dto.SystemConfigDTO;

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
     * 獲取系統配置
     *
     * @return 系統配置
     */
    SystemConfigDTO getSystemConfig();

    /**
     * 獲取RSA密鑰
     *
     * @return 密鑰
     */
    String getRsaKey();

    /**
     * 刷新密鑰
     *
     * @return 新的數據密鑰（以加密）
     */
    String refreshKey();

    /**
     * 刪除數據密鑰
     *
     * @return 結果
     */
    Boolean removeSalt();

    /**
     * 更新
     *
     * @param dto 實體
     * @return 結果
     */
    Boolean updateSystemConfig(SystemConfigDTO dto);
}
