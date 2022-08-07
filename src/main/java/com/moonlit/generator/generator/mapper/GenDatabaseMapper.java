package com.moonlit.generator.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moonlit.generator.generator.entity.GenDatabase;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据库配置映射层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:34
 * @email by.Moonlit@hotmail.com
 */
@Mapper
public interface GenDatabaseMapper extends BaseMapper<GenDatabase> {

}
