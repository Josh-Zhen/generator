package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenTables;

/**
 * 库表生成_表业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:18
 * @email by.Moonlit@hotmail.com
 */
public interface GenTablesService extends IService<GenTables> {

    /**
     * 条件分页查询
     *
     * @param tables 表实体
     * @return 结果集
     */
    PageResult<GenTables> pageList(GenTables tables);

    /**
     * 新增
     *
     * @param tables 表实体
     * @return 结果
     */
    Boolean insertTables(GenTables tables);

    /**
     * 修改
     *
     * @param tables 表实体
     * @return 结果
     */
    Boolean updateTables(GenTables tables);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteTablesByIds(String ids);


}
