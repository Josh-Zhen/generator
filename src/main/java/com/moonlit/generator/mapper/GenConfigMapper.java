package com.moonlit.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moonlit.generator.entity.GenConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * 作者相关配置Mapper层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 9:09
 * @email by.Moonlit@hotmail.com
 */
@Mapper
public interface GenConfigMapper extends BaseMapper<GenConfig> {

    /**
     * 查询状态为默认的
     *
     * @return 对象
     */
    GenConfig getConfigByType();
}
