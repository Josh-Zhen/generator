package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenDatabase;

/**
 * 连接库业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:28
 * @email by.Moonlit@hotmail.com
 */
public interface GenDbService extends IService<GenDatabase> {

    /**
     * 条件分页查询
     *
     * @param genDatabase 表实体
     * @return 结果集
     */
    PageResult<GenDatabase> pageList(GenDatabase genDatabase);

    /**
     * 新增
     *
     * @param genDatabase 表实体
     * @return 结果
     */
    Boolean insertDbDetail(GenDatabase genDatabase);

    /**
     * 修改
     *
     * @param genDatabase 表实体
     * @return 结果
     */
    Boolean updateDbDetail(GenDatabase genDatabase);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteDbDetailByIds(String ids);
    
}
