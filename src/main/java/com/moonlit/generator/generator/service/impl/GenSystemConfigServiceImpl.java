package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.encrypt.RsaUtils;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.generator.constants.error.DatabaseErrorCode;
import com.moonlit.generator.generator.entity.GenSystemConfig;
import com.moonlit.generator.generator.entity.dto.SystemConfigDTO;
import com.moonlit.generator.generator.mapper.GenSystemConfigMapper;
import com.moonlit.generator.generator.service.GenDatabaseService;
import com.moonlit.generator.generator.service.GenSystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * 系統配置业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 9:08
 * @email by.Moonlit@hotmail.com
 */
@Service
public class GenSystemConfigServiceImpl extends ServiceImpl<GenSystemConfigMapper, GenSystemConfig> implements GenSystemConfigService {

    @Autowired
    private GenDatabaseService databaseService;

    /**
     * 獲取系統配置
     *
     * @return 系統配置
     */
    @Override
    public SystemConfigDTO getSystemConfig() {
        return baseMapper.getSystemConfig();
    }

    /**
     * 獲取密鑰
     *
     * @return 密鑰
     */
    @Override
    public String getRsaKey() {
        GenSystemConfig systemConfig = baseMapper.selectById(1);
        String salt = systemConfig.getSalt();
        if (ObjectUtil.isEmpty(salt)) {
            throw new BusinessException(DatabaseErrorCode.KEY_NOT_SET);
        }
        return RsaUtils.publicDecrypt(salt, systemConfig.getPublicKey());
    }

    /**
     * 刷新密鑰
     *
     * @return 新的數據密鑰（以加密）
     */
    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public String refreshKey() {
        GenSystemConfig systemConfig = this.getById(1);
        // 获取新密钥
        HashMap<String, String> keys = RsaUtils.genKeyPair();
        systemConfig.setPublicKey(keys.get("publicKey"));
        String privateKey = keys.get("privateKey");
        systemConfig.setPrivateKey(privateKey);
        // 新的數據密鑰
        String salt = RandomUtil.randomString(10);
        String dataKey = RsaUtils.privateEncrypt(salt, privateKey);
        if (systemConfig.getState()) {
            systemConfig.setSalt(dataKey);
        }
        systemConfig.setUpdateDate(LocalDateTime.now());

        // 更新數據庫連接的用戶名與密碼
        databaseService.updateDatabasesInData(salt);
        this.updateById(systemConfig);
        return dataKey;
    }

    /**
     * 刪除數據密鑰
     *
     * @return 結果
     */
    @Override
    public Boolean removeSalt() {
        GenSystemConfig systemConfig = this.getById(1L);
        systemConfig.setSalt("");
        return this.updateById(systemConfig);
    }

    /**
     * 更新
     *
     * @param dto 實體
     * @return 結果
     */
    @Override
    public Boolean updateSystemConfig(SystemConfigDTO dto) {
        GenSystemConfig systemConfig = this.getById(1L);
        if (ObjectUtil.isNotEmpty(dto.getState())) {
            systemConfig.setState(dto.getState());
        }
        systemConfig.setSalt(dto.getSalt());
        return this.updateById(systemConfig);
    }

}
