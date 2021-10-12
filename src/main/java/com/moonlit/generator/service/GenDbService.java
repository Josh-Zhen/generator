package com.moonlit.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.entity.GenDb;

/**
 * 连接库业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:28
 * @email by.Moonlit@hotmail.com
 */
public interface GenDbService extends IService<GenDb> {

    /**
     * 条件分页查询
     *
     * @param genDb 表实体
     * @return 结果集
     */
    PageResult<GenDb> pageList(GenDb genDb);

    /**
     * 新增
     *
     * @param genDb 表实体
     * @return 结果
     */
    Boolean insertDbDetail(GenDb genDb);

    /**
     * 修改
     *
     * @param genDb 表实体
     * @return 结果
     */
    Boolean updateDbDetail(GenDb genDb);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteDbDetailByIds(String ids);
}
