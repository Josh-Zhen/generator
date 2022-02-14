package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenTablesConfig;

/**
 * 表配置业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 9:06
 * @email by.Moonlit@hotmail.com
 */
public interface GenTablesConfigService extends IService<GenTablesConfig> {

    /**
     * 条件分页查询
     *
     * @param genTablesConfig 表实体
     * @return 结果集
     */
    PageResult<GenTablesConfig> pageList(GenTablesConfig genTablesConfig);

    /**
     * 新增
     *
     * @param genTablesConfig 表实体
     * @return 结果
     */
    Boolean insertDbDetail(GenTablesConfig genTablesConfig);

    /**
     * 修改
     *
     * @param genTablesConfig 表实体
     * @return 结果
     */
    Boolean updateDbDetail(GenTablesConfig genTablesConfig);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteDbDetailByIds(String ids);
}
