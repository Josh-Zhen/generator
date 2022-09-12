package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenFieldMapping;
import com.moonlit.generator.generator.mapper.GenFieldMappingMapper;
import com.moonlit.generator.generator.service.GenFieldMappingService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 键值映射业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 12/09/2022 23:11
 */
@Service
public class GenFieldMappingServiceImpl extends ServiceImpl<GenFieldMappingMapper, GenFieldMapping> implements GenFieldMappingService {

    /**
     * 分页查询键值映射
     *
     * @param fieldMapping 键值映射实体
     * @return 键值映射集合
     */
    @Override
    public PageResult<GenFieldMapping> pageList(GenFieldMapping fieldMapping) {
        LambdaQueryWrapper<GenFieldMapping> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotNull(fieldMapping)) {
            if (ObjectUtil.isNotEmpty(fieldMapping.getId())) {
                queryWrapper.eq(GenFieldMapping::getId, fieldMapping.getId());
            }
            if (ObjectUtil.isNotEmpty(fieldMapping.getComment())) {
                queryWrapper.eq(GenFieldMapping::getComment, fieldMapping.getComment());
            }
            if (ObjectUtil.isNotEmpty(fieldMapping.getMapping())) {
                queryWrapper.eq(GenFieldMapping::getMapping, fieldMapping.getMapping());
            }
            if (ObjectUtil.isNotEmpty(fieldMapping.getType())) {
                queryWrapper.eq(GenFieldMapping::getType, fieldMapping.getType());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 查询键值映射列表
     *
     * @param fieldMapping 键值映射实体
     * @return 键值映射集合
     */
    @Override
    public List<GenFieldMapping> selectFieldMappingList(GenFieldMapping fieldMapping) {
        LambdaQueryWrapper<GenFieldMapping> queryWrapper = Wrappers.lambdaQuery();
        return this.list(queryWrapper);
    }

    /**
     * 新增键值映射
     *
     * @param fieldMapping 键值映射实体
     * @return 结果
     */
    @Override
    public Boolean insertFieldMapping(GenFieldMapping fieldMapping) {
        return this.save(fieldMapping);
    }

    /**
     * 修改键值映射
     *
     * @param fieldMapping mapping实体
     * @return 结果
     */
    @Override
    public Boolean updateFieldMapping(GenFieldMapping fieldMapping) {
        return this.updateById(fieldMapping);
    }

    /**
     * 批量删除键值映射
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Boolean deleteFieldMappingByIds(String ids) {
        return this.removeByIds(Arrays.asList(Convert.toStrArray(ids)));
    }

}