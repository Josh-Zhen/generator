package com.moonlit.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.entity.DbDetail;

/**
 * 数据库明细业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:28
 * @email by.Moonlit@hotmail.com
 */
public interface DbDetailService extends IService<DbDetail> {

    /**
     * 条件分页查询
     *
     * @param dbDetail 表实体
     * @return 结果集
     */
    PageResult<DbDetail> pageList(DbDetail dbDetail);

    /**
     * 新增
     *
     * @param dbDetail 表实体
     * @return 结果
     */
    Boolean insertDbDetail(DbDetail dbDetail);

    /**
     * 修改
     *
     * @param dbDetail 表实体
     * @return 结果
     */
    Boolean updateDbDetail(DbDetail dbDetail);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteDbDetailByIds(String ids);
}
