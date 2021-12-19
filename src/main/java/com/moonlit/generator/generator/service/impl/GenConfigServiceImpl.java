package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.constants.error.DatabaseErrorCode;
import com.moonlit.generator.generator.entity.GenConfig;
import com.moonlit.generator.generator.mapper.GenConfigMapper;
import com.moonlit.generator.generator.service.GenConfigService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 作者相关配置业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 9:08
 * @email by.Moonlit@hotmail.com
 */
@Service
public class GenConfigServiceImpl extends ServiceImpl<GenConfigMapper, GenConfig> implements GenConfigService {

    /**
     * 条件分页查询
     *
     * @param genConfig 表实体
     * @return 结果集
     */
    @Override
    public PageResult<GenConfig> pageList(GenConfig genConfig) {
        LambdaQueryWrapper<GenConfig> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotNull(genConfig)) {
            if (ObjectUtil.isNotEmpty(genConfig.getAuthor())) {
                queryWrapper.eq(GenConfig::getAuthor, genConfig.getAuthor());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增
     *
     * @param genConfig 表实体
     * @return 结果
     */
    @Override
    public Boolean insertDbDetail(GenConfig genConfig) {
        genConfig.setCreateDate(LocalDateTime.now());
        checkOnlyOneAndType(genConfig);
        return this.save(genConfig);
    }

    /**
     * 修改
     *
     * @param genConfig 表实体
     * @return 结果
     */
    @Override
    public Boolean updateDbDetail(GenConfig genConfig) {
        genConfig.setUpdateDate(LocalDateTime.now());
        checkOnlyOneAndType(genConfig);
        return this.updateById(genConfig);
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

    /**
     * 保证只有一个唯一的作者信息
     *
     * @param genConfig 默认值
     */
    private void checkOnlyOneAndType(GenConfig genConfig) {
        LambdaQueryWrapper<GenConfig> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(GenConfig::getAuthor, genConfig.getAuthor());
        if (!genConfig.getId().equals(baseMapper.selectOne(queryWrapper).getId())) {
            throw new BusinessException(DatabaseErrorCode.DATA_IS_TRUE);
        }

        if (genConfig.getType() == 1) {
            queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(GenConfig::getType, genConfig.getType());
            GenConfig one = baseMapper.selectOne(queryWrapper);
            // 存在其他默认时，将其他默认修改为非默认
            if (ObjectUtil.isNotEmpty(one)) {
                one.setType(0);
                this.updateById(one);
            }
        }

    }
}
