package com.moonlit.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.entity.DbDetail;
import com.moonlit.generator.entity.GenTables;
import com.moonlit.generator.entity.GenTablesColumn;
import com.moonlit.generator.mapper.GenTablesColumnMapper;
import com.moonlit.generator.service.GenTablesColumnService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 库表生成_表字段明细业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:03
 * @email by.Moonlit@hotmail.com
 */
@Service
public class GenTablesColumnServiceImpl extends ServiceImpl<GenTablesColumnMapper, GenTablesColumn> implements GenTablesColumnService {

    /**
     * 分页查询
     *
     * @param genTablesColumn 查询条件
     * @return 集合
     */
    @Override
    public PageResult<GenTables> pageList(GenTablesColumn genTablesColumn) {
        LambdaQueryWrapper<GenTablesColumn> queryWrapper = Wrappers.lambdaQuery();
        return null;
    }

    /**
     * 新增
     *
     * @param genTablesColumn 实体
     * @return 结果
     */
    @Override
    public Boolean insertTablesColumn(GenTablesColumn genTablesColumn) {
        genTablesColumn.setCreateTime(LocalDateTime.now());
        return this.save(genTablesColumn);
    }

    /**
     * 修改
     *
     * @param genTablesColumn 实体
     * @return 结果
     */
    @Override
    public Boolean updateTablesColumn(GenTablesColumn genTablesColumn) {
        genTablesColumn.setUpdateTime(LocalDateTime.now());
        return this.updateById(genTablesColumn);
    }

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public Boolean deleteTablesColumnByIds(String ids) {
        return this.removeByIds(Arrays.asList(Convert.toStrArray(ids)));
    }
}
