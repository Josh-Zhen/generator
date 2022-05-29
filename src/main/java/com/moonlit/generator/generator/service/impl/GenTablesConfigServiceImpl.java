package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenTablesConfig;
import com.moonlit.generator.generator.entity.dto.GenTablesConfigDTO;
import com.moonlit.generator.generator.mapper.GenTablesConfigMapper;
import com.moonlit.generator.generator.service.GenTablesConfigService;
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
public class GenTablesConfigServiceImpl extends ServiceImpl<GenTablesConfigMapper, GenTablesConfig> implements GenTablesConfigService {

    /**
     * 条件分页查询
     *
     * @param tablesConfigDTO 表实体
     * @return 结果集
     */
    @Override
    public PageResult<GenTablesConfig> pageList(GenTablesConfigDTO tablesConfigDTO) {
        LambdaQueryWrapper<GenTablesConfig> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotEmpty(tablesConfigDTO)) {
            if (ObjectUtil.isNotEmpty(tablesConfigDTO.getName())) {
                queryWrapper.like(GenTablesConfig::getName, tablesConfigDTO.getName());
            }
            if (ObjectUtil.isNotEmpty(tablesConfigDTO.getAuthor())) {
                queryWrapper.like(GenTablesConfig::getAuthor, tablesConfigDTO.getAuthor());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增
     *
     * @param genTablesConfig 包实体
     * @return 结果
     */
    @Override
    public Boolean insertDbDetail(GenTablesConfig genTablesConfig) {
        genTablesConfig.setCreateDate(LocalDateTime.now());
        return this.save(genTablesConfig);
    }

    /**
     * 修改
     *
     * @param genTablesConfig 表实体
     * @return 结果
     */
    @Override
    public Boolean updateDbDetail(GenTablesConfig genTablesConfig) {
        return this.updateById(genTablesConfig);
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
