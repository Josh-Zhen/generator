package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenTableConfig;
import com.moonlit.generator.generator.entity.dto.GenTableConfigDTO;
import com.moonlit.generator.generator.mapper.GenTableConfigMapper;
import com.moonlit.generator.generator.service.GenTableConfigService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 表配置业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 9:08
 * @email by.Moonlit@hotmail.com
 */
@Service
public class GenTableConfigServiceImpl extends ServiceImpl<GenTableConfigMapper, GenTableConfig> implements GenTableConfigService {

    /**
     * 条件分页查询
     *
     * @param tableConfigDTO 表实体
     * @return 结果集
     */
    @Override
    public PageResult<GenTableConfig> pageList(GenTableConfigDTO tableConfigDTO) {
        LambdaQueryWrapper<GenTableConfig> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotEmpty(tableConfigDTO)) {
            if (ObjectUtil.isNotEmpty(tableConfigDTO.getName())) {
                queryWrapper.like(GenTableConfig::getName, tableConfigDTO.getName());
            }
            if (ObjectUtil.isNotEmpty(tableConfigDTO.getAuthor())) {
                queryWrapper.like(GenTableConfig::getAuthor, tableConfigDTO.getAuthor());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增
     *
     * @param genTableConfig 包实体
     * @return 结果
     */
    @Override
    public Boolean insertDbDetail(GenTableConfig genTableConfig) {
        genTableConfig.setCreateDate(LocalDateTime.now());
        return this.save(genTableConfig);
    }

    /**
     * 修改
     *
     * @param genTableConfig 表实体
     * @return 结果
     */
    @Override
    public Boolean updateDbDetail(GenTableConfig genTableConfig) {
        return this.updateById(genTableConfig);
    }

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Boolean deleteDbDetailByIds(String ids) {
        return this.removeByIds(Arrays.asList(Convert.toStrArray(ids)));
    }

}
