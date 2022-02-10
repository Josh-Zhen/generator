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
import com.moonlit.generator.generator.entity.GenAuthorConfig;
import com.moonlit.generator.generator.entity.dto.GenAuthorConfigDTO;
import com.moonlit.generator.generator.mapper.GenAuthorConfigMapper;
import com.moonlit.generator.generator.service.GenAuthorConfigService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 作者配置业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 9:08
 * @email by.Moonlit@hotmail.com
 */
@Service
public class GenAuthorConfigServiceImpl extends ServiceImpl<GenAuthorConfigMapper, GenAuthorConfig> implements GenAuthorConfigService {

    /**
     * 条件分页查询
     *
     * @param genAuthorConfigDTO 表实体
     * @return 结果集
     */
    @Override
    public PageResult<GenAuthorConfig> pageList(GenAuthorConfigDTO genAuthorConfigDTO) {
        LambdaQueryWrapper<GenAuthorConfig> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotNull(genAuthorConfigDTO)) {
            if (ObjectUtil.isNotEmpty(genAuthorConfigDTO.getAuthor())) {
                queryWrapper.eq(GenAuthorConfig::getAuthor, genAuthorConfigDTO.getAuthor());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增
     *
     * @param genAuthorConfig 表实体
     * @return 结果
     */
    @Override
    public Boolean insertDbDetail(GenAuthorConfig genAuthorConfig) {
        genAuthorConfig.setCreateDate(LocalDateTime.now());
        checkOnlyOneAndType(genAuthorConfig);
        return this.save(genAuthorConfig);
    }

    /**
     * 修改
     *
     * @param genAuthorConfig 表实体
     * @return 结果
     */
    @Override
    public Boolean updateDbDetail(GenAuthorConfig genAuthorConfig) {
        genAuthorConfig.setUpdateDate(LocalDateTime.now());
        checkOnlyOneAndType(genAuthorConfig);
        return this.updateById(genAuthorConfig);
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
     * @param genAuthorConfig 默认值
     */
    private void checkOnlyOneAndType(GenAuthorConfig genAuthorConfig) {
        LambdaQueryWrapper<GenAuthorConfig> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(GenAuthorConfig::getAuthor, genAuthorConfig.getAuthor());
        if (!genAuthorConfig.getId().equals(baseMapper.selectOne(queryWrapper).getId())) {
            throw new BusinessException(DatabaseErrorCode.DATA_IS_TRUE);
        }

        if (genAuthorConfig.getType() == 1) {
            queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.eq(GenAuthorConfig::getType, genAuthorConfig.getType());
            GenAuthorConfig one = baseMapper.selectOne(queryWrapper);
            // 存在其他默认时，将其他默认修改为非默认
            if (ObjectUtil.isNotEmpty(one)) {
                one.setType(0);
                this.updateById(one);
            }
        }

    }
}
