package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenAuthorConfig;
import com.moonlit.generator.generator.entity.dto.GenAuthorConfigDTO;

/**
 * 作者配置业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 9:06
 * @email by.Moonlit@hotmail.com
 */
public interface GenAuthorConfigService extends IService<GenAuthorConfig> {

    /**
     * 条件分页查询
     *
     * @param genAuthorConfigDTO 表实体
     * @return 结果集
     */
    PageResult<GenAuthorConfig> pageList(GenAuthorConfigDTO genAuthorConfigDTO);

    /**
     * 新增
     *
     * @param genAuthorConfig 表实体
     * @return 结果
     */
    Boolean insertDbDetail(GenAuthorConfig genAuthorConfig);

    /**
     * 修改
     *
     * @param genAuthorConfig 表实体
     * @return 结果
     */
    Boolean updateDbDetail(GenAuthorConfig genAuthorConfig);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteDbDetailByIds(String ids);
}
