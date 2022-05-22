package com.moonlit.generator.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.encrypt.RsaUtils;
import com.moonlit.generator.generator.entity.GenSystemConfig;
import com.moonlit.generator.generator.mapper.GenSystemConfigMapper;
import com.moonlit.generator.generator.service.GenSystemConfigService;
import org.springframework.stereotype.Service;

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

    /**
     * 獲取密鑰
     *
     * @return 密鑰
     */
    @Override
    public String getRsaKey() {
        GenSystemConfig systemConfig = baseMapper.selectById(1);
        return RsaUtils.publicDecrypt(systemConfig.getSalt(), systemConfig.getPublicKey());
    }
}
