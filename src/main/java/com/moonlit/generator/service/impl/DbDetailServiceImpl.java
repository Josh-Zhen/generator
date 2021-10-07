package com.moonlit.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.entity.DbDetail;
import com.moonlit.generator.mapper.DbDetailMapper;
import com.moonlit.generator.service.DbDetailService;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 数据库明细业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:33
 * @email by.Moonlit@hotmail.com
 */
public class DbDetailServiceImpl extends ServiceImpl<DbDetailMapper, DbDetail> implements DbDetailService {

    /**
     * 条件分页查询
     *
     * @param dbDetail 表实体
     * @return 结果集
     */
    @Override
    public PageResult<DbDetail> pageList(DbDetail dbDetail) {
        LambdaQueryWrapper<DbDetail> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotNull(dbDetail)) {
            if (ObjectUtil.isNotEmpty(dbDetail.getDbName())) {
                queryWrapper.eq(DbDetail::getDbName, dbDetail.getDbName());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增
     *
     * @param dbDetail 表实体
     * @return 结果
     */
    @Override
    public Boolean insertDbDetail(DbDetail dbDetail) {
        // TODO 用户密码需要加密
//        dbDetail.setPassword(dbDetail.getPassword());
        dbDetail.setCreateDate(LocalDateTime.now());
        return this.save(dbDetail);
    }

    /**
     * 修改
     *
     * @param dbDetail 表实体
     * @return 结果
     */
    @Override
    public Boolean updateDbDetail(DbDetail dbDetail) {
        dbDetail.setUpdateDate(LocalDateTime.now());
        return this.updateById(dbDetail);
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
