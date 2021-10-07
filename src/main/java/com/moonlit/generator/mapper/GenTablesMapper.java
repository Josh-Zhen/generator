package com.moonlit.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moonlit.generator.entity.GenTables;
import org.apache.ibatis.annotations.Mapper;

/**
 * 库表生成_表Mapper层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:05
 * @email by.Moonlit@hotmail.com
 */
@Mapper
public interface GenTablesMapper extends BaseMapper<GenTables> {

}
