package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.constants.error.FieldMappingErrorCode;
import com.moonlit.generator.generator.entity.GenFieldMapping;
import com.moonlit.generator.generator.mapper.GenFieldMappingMapper;
import com.moonlit.generator.generator.service.GenFieldMappingService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

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
        LambdaQueryWrapper<GenFieldMapping> queryWrapper = null;
        if (ObjectUtil.isNotEmpty(fieldMapping)) {
            queryWrapper = Wrappers.lambdaQuery(fieldMapping);
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 查询啓用的键值映射集合s
     *
     * @return 键值映射集合
     */
    @Override
    public HashMap<String, Object> selectFieldMappingList() {
        HashMap<String, Object> map = new HashMap<>(0);
        LambdaQueryWrapper<GenFieldMapping> query = Wrappers.lambdaQuery();
        query.eq(GenFieldMapping::getState, true);
        for (GenFieldMapping fieldMapping : this.list(query)) {
            switch (fieldMapping.getType()) {
                case 1:
                    // 布爾類型
                    map.put(fieldMapping.getComment(), Boolean.valueOf(fieldMapping.getMapping()));
                    break;
                case 2:
                    // 数字类型
                    map.put(fieldMapping.getComment(), Integer.valueOf(fieldMapping.getMapping()));
                    break;
                case 3:
                    // 數組類型
                    map.put(fieldMapping.getComment(), Collections.singletonList(fieldMapping.getMapping()));
                    break;
                default:
                    // 字符串類型
                    map.put(fieldMapping.getComment(), fieldMapping.getMapping());
                    break;
            }
        }
        return map;
    }

    /**
     * 新增键值映射
     *
     * @param fieldMapping 键值映射实体
     * @return 结果
     */
    @Override
    public Boolean insertFieldMapping(GenFieldMapping fieldMapping) {
        judgeComment(fieldMapping);
        return this.save(fieldMapping);
    }

    /**
     * 修改键值映射
     *
     * @param fieldMapping 键值映射实体
     * @return 结果
     */
    @Override
    public Boolean updateFieldMapping(GenFieldMapping fieldMapping) {
        judgeComment(fieldMapping);
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

    /*---------------------------------------- 内部方法 ----------------------------------------*/

    /**
     * 判斷是否存在已啓用的鍵
     *
     * @param fieldMapping 键值映射实体
     */
    private void judgeComment(GenFieldMapping fieldMapping) {
        LambdaQueryWrapper<GenFieldMapping> query = Wrappers.lambdaQuery();
        query.eq(GenFieldMapping::getComment, fieldMapping.getComment())
                .eq(GenFieldMapping::getState, fieldMapping.getState());
        if (ObjectUtil.isNotEmpty(this.getOne(query))) {
            throw new BusinessException(FieldMappingErrorCode.COMMENT_IS_EXIST);
        }
    }
}