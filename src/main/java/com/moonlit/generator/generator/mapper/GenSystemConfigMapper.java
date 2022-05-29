package com.moonlit.generator.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moonlit.generator.generator.entity.GenSystemConfig;
import com.moonlit.generator.generator.entity.dto.SystemConfigDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系統配置Mapper层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 9:09
 * @email by.Moonlit@hotmail.com
 */
@Mapper
public interface GenSystemConfigMapper extends BaseMapper<GenSystemConfig> {

    /**
     * 獲取系統配置
     *
     * @return 系統配置
     */
    SystemConfigDTO getSystemConfig();
}
