package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenPackageConfig;
import com.moonlit.generator.generator.mapper.GenPackageConfigMapper;
import com.moonlit.generator.generator.service.GenPackageConfigService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 包配置业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 9:08
 * @email by.Moonlit@hotmail.com
 */
@Service
public class GenPackageConfigServiceImpl extends ServiceImpl<GenPackageConfigMapper, GenPackageConfig> implements GenPackageConfigService {

    /**
     * 条件分页查询
     *
     * @param genPackageConfig 表实体
     * @return 结果集
     */
    @Override
    public PageResult<GenPackageConfig> pageList(GenPackageConfig genPackageConfig) {
        LambdaQueryWrapper<GenPackageConfig> queryWrapper = Wrappers.lambdaQuery();
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增
     *
     * @param genPackageConfig 包实体
     * @return 结果
     */
    @Override
    public Boolean insertDbDetail(GenPackageConfig genPackageConfig) {
        genPackageConfig.setCreateDate(LocalDateTime.now());
        return this.save(genPackageConfig);
    }

    /**
     * 修改
     *
     * @param genPackageConfig 表实体
     * @return 结果
     */
    @Override
    public Boolean updateDbDetail(GenPackageConfig genPackageConfig) {
        return this.updateById(genPackageConfig);
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
