package com.moonlit.generator.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.entity.GenDb;
import com.moonlit.generator.entity.vo.GenDbVo;
import com.moonlit.generator.mapper.DbDetailMapper;
import com.moonlit.generator.service.GenDbService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 连接库业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:33
 * @email by.Moonlit@hotmail.com
 */
@Service
public class GenDbServiceImpl extends ServiceImpl<DbDetailMapper, GenDb> implements GenDbService {

    /**
     * 条件分页查询
     *
     * @param genDb 表实体
     * @return 结果集
     */
    @Override
    public PageResult<GenDbVo> pageList(GenDb genDb) {
        return new PageResult<>(baseMapper.selectAll(PageFactory.defaultPage(), genDb));
    }

    /**
     * 新增
     *
     * @param genDb 表实体
     * @return 结果
     */
    @Override
    public Boolean insertDbDetail(GenDb genDb) {
        // TODO 用户密码需要加密
//        dbDetail.setPassword(dbDetail.getPassword());
        genDb.setCreateDate(LocalDateTime.now());
        return this.save(genDb);
    }

    /**
     * 修改
     *
     * @param genDb 表实体
     * @return 结果
     */
    @Override
    public Boolean updateDbDetail(GenDb genDb) {
        // TODO 须校验用户名与密码是否修改过

        genDb.setUpdateDate(LocalDateTime.now());
        return this.updateById(genDb);
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
