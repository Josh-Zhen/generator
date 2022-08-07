package com.moonlit.generator.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moonlit.generator.generator.entity.GenTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 數據表配置映射层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:05
 * @email by.Moonlit@hotmail.com
 */
@Mapper
public interface GenTableMapper extends BaseMapper<GenTable> {

    /**
     * 根據庫id獲取表明
     *
     * @param databaseId 庫id
     * @return 表名集合
     */
    List<String> selectTableNames(Long databaseId);
}
