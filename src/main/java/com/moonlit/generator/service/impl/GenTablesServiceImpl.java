package com.moonlit.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.entity.GenTables;
import com.moonlit.generator.mapper.GenTablesMapper;
import com.moonlit.generator.service.GenTablesService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 库表生成_表业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:21
 * @email by.Moonlit@hotmail.com
 */
@Service
public class GenTablesServiceImpl extends ServiceImpl<GenTablesMapper, GenTables> implements GenTablesService {

    /**
     * 条件分页查询
     *
     * @param genTables 表实体
     * @return 结果集
     */
    @Override
    public PageResult<GenTables> pageList(GenTables genTables) {
        LambdaQueryWrapper<GenTables> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotNull(genTables)) {
            if (ObjectUtil.isNotEmpty(genTables.getDbName())) {
                queryWrapper.eq(GenTables::getDbName, genTables.getDbName());
            }
            if (ObjectUtil.isNotEmpty(genTables.getTableName())) {
                queryWrapper.eq(GenTables::getTableName, genTables.getTableName());
            }
            if (ObjectUtil.isNotEmpty(genTables.getPackageName())) {
                queryWrapper.eq(GenTables::getPackageName, genTables.getPackageName());
            }
            if (ObjectUtil.isNotEmpty(genTables.getModuleName())) {
                queryWrapper.eq(GenTables::getModuleName, genTables.getModuleName());
            }
            if (ObjectUtil.isNotEmpty(genTables.getClassName())) {
                queryWrapper.eq(GenTables::getClassName, genTables.getClassName());
            }
            if (ObjectUtil.isNotEmpty(genTables.getCreateName())) {
                queryWrapper.eq(GenTables::getCreateName, genTables.getCreateName());
            }
            if (ObjectUtil.isNotEmpty(genTables.getCreateDate())) {
                queryWrapper.eq(GenTables::getCreateDate, genTables.getCreateDate());
            }
            if (ObjectUtil.isNotEmpty(genTables.getUpdateDate())) {
                queryWrapper.eq(GenTables::getUpdateDate, genTables.getUpdateDate());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增
     *
     * @param genTables 表实体
     * @return 结果
     */
    @Override
    public Boolean insertTables(GenTables genTables) {
        genTables.setCreateDate(LocalDateTime.now());
        return this.save(genTables);
    }

    /**
     * 修改
     *
     * @param genTables 表实体
     * @return 结果
     */
    @Override
    public Boolean updateTables(GenTables genTables) {
        genTables.setUpdateDate(LocalDateTime.now());
        return this.updateById(genTables);
    }

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Boolean deleteTablesByIds(String ids) {
        return this.removeByIds(Arrays.asList(Convert.toStrArray(ids)));
    }
}
