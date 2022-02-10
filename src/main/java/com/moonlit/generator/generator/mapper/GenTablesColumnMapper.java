package com.moonlit.generator.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moonlit.generator.generator.entity.GenTablesColumn;
import org.apache.ibatis.annotations.Mapper;

/**
 * 數據表字段明细Mapper层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:14
 * @email by.Moonlit@hotmail.com
 */
@Mapper
public interface GenTablesColumnMapper extends BaseMapper<GenTablesColumn> {
    
}
