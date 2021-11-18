package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenConfig;

/**
 * 作者相关配置业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 9:06
 * @email by.Moonlit@hotmail.com
 */
public interface GenConfigService extends IService<GenConfig> {

    /**
     * 条件分页查询
     *
     * @param genConfig 表实体
     * @return 结果集
     */
    PageResult<GenConfig> pageList(GenConfig genConfig);

    /**
     * 新增
     *
     * @param genConfig 表实体
     * @return 结果
     */
    Boolean insertDbDetail(GenConfig genConfig);

    /**
     * 修改
     *
     * @param genConfig 表实体
     * @return 结果
     */
    Boolean updateDbDetail(GenConfig genConfig);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteDbDetailByIds(String ids);
}
