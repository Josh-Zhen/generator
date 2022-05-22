package com.moonlit.generator.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moonlit.generator.generator.entity.GenTablesColumn;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

/**
 * 數據表字段詳情Mapper层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:14
 * @email by.Moonlit@hotmail.com
 */
@Mapper
public interface GenTablesColumnMapper extends BaseMapper<GenTablesColumn> {

    /**
     * 根據表id查詢所有表字段
     *
     * @param tableId 表id
     * @return 結果集
     */
    Collection<String> selectTablesName(Long tableId);
}
