package com.moonlit.generator.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moonlit.generator.generator.entity.GenDatabase;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据库明细Mapper层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:34
 * @email by.Moonlit@hotmail.com
 */
@Mapper
public interface GenDatabaseMapper extends BaseMapper<GenDatabase> {

    /**
     * 条件查询
     *
     * @param defaultPage 分页实体
     * @param genDatabase 查询条件
     * @return 结果集
     */
    Page<GenDatabase> selectAll(Page<Object> defaultPage, GenDatabase genDatabase);

}
