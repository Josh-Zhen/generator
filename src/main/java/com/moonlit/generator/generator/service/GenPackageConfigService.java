package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenPackageConfig;

/**
 * 包配置业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 9:06
 * @email by.Moonlit@hotmail.com
 */
public interface GenPackageConfigService extends IService<GenPackageConfig> {

    /**
     * 条件分页查询
     *
     * @param genPackageConfig 表实体
     * @return 结果集
     */
    PageResult<GenPackageConfig> pageList(GenPackageConfig genPackageConfig);

    /**
     * 新增
     *
     * @param genPackageConfig 表实体
     * @return 结果
     */
    Boolean insertDbDetail(GenPackageConfig genPackageConfig);

    /**
     * 修改
     *
     * @param genPackageConfig 表实体
     * @return 结果
     */
    Boolean updateDbDetail(GenPackageConfig genPackageConfig);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteDbDetailByIds(String ids);
}
