package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenTableConfig;
import com.moonlit.generator.generator.entity.dto.GenTableConfigDTO;

/**
 * 表配置业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 9:06
 * @email by.Moonlit@hotmail.com
 */
public interface GenTableConfigService extends IService<GenTableConfig> {

    /**
     * 条件分页查询
     *
     * @param tablesConfigDTO 表实体
     * @return 结果集
     */
    PageResult<GenTableConfig> pageList(GenTableConfigDTO tablesConfigDTO);

    /**
     * 新增
     *
     * @param genTableConfig 表实体
     * @return 结果
     */
    Boolean insertTableConfig(GenTableConfig genTableConfig);

    /**
     * 修改
     *
     * @param genTableConfig 表实体
     * @return 结果
     */
    Boolean updateTableConfig(GenTableConfig genTableConfig);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteTableConfigByIds(String ids);

}
