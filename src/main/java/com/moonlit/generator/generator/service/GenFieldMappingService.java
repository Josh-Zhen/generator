package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenFieldMapping;

import java.util.List;

/**
 * 键值映射业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 12/09/2022 23:11
 */
public interface GenFieldMappingService extends IService<GenFieldMapping> {

    /**
     * 分页查询键值映射
     *
     * @param fieldMapping 键值映射实体
     * @return 键值映射集合
     */
    PageResult<GenFieldMapping> pageList(GenFieldMapping fieldMapping);

    /**
     * 查询键值映射列表
     *
     * @param fieldMapping 键值映射实体
     * @return 键值映射集合
     */
    List<GenFieldMapping> selectFieldMappingList(GenFieldMapping fieldMapping);

    /**
     * 新增键值映射
     *
     * @param fieldMapping 键值映射实体
     * @return 结果
     */
    Boolean insertFieldMapping(GenFieldMapping fieldMapping);

    /**
     * 修改键值映射
     *
     * @param fieldMapping 键值映射实体
     * @return 结果
     */
    Boolean updateFieldMapping(GenFieldMapping fieldMapping);

    /**
     * 批量删除键值映射
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteFieldMappingByIds(String ids);

}
