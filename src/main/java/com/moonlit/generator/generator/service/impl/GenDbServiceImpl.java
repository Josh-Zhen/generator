package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.encrypt.RsaUtils;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenDatabase;
import com.moonlit.generator.generator.mapper.GenConfigMapper;
import com.moonlit.generator.generator.mapper.GenDatabaseMapper;
import com.moonlit.generator.generator.service.GenDbService;
import com.moonlit.generator.generator.service.GenTablesService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class GenDbServiceImpl extends ServiceImpl<GenDatabaseMapper, GenDatabase> implements GenDbService {

    @Autowired
    private GenConfigMapper genConfigMapper;
    @Autowired
    private GenTablesService genTablesService;


    /**
     * 条件分页查询
     *
     * @param genDatabase 表实体
     * @return 结果集
     */
    @Override
    public PageResult<GenDatabase> pageList(GenDatabase genDatabase) {
        return new PageResult<>(baseMapper.selectAll(PageFactory.defaultPage(), genDatabase));
    }

    /**
     * 新增
     *
     * @param genDatabase 表实体
     * @return 结果
     */
    @Override
    public Boolean insertDbDetail(GenDatabase genDatabase) {
        genDatabase.setUserName(encrypt(genDatabase.getUserName()));
        genDatabase.setPassword(encrypt(genDatabase.getPassword()));
        genDatabase.setCreateDate(LocalDateTime.now());
        return this.save(genDatabase);
    }

    /**
     * 修改
     *
     * @param genDatabase 表实体
     * @return 结果
     */
    @Override
    public Boolean updateDbDetail(GenDatabase genDatabase) {
        GenDatabase db = this.getById(genDatabase.getId());

        // 校验用户名与密码是否修改过
        if (!db.getUserName().equals(genDatabase.getUserName())) {
            genDatabase.setUserName(encrypt(genDatabase.getUserName()));
        }
        if (!db.getPassword().equals(genDatabase.getPassword())) {
            genDatabase.setPassword(encrypt(genDatabase.getPassword()));
        }
        genDatabase.setUpdateDate(LocalDateTime.now());
        return this.updateById(genDatabase);
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

    /*---------------------------------------- 内部方法 ----------------------------------------*/

    /**
     * RSA数据加密
     *
     * @param data 数据
     * @return 结果
     */
    private String encrypt(String data) {
        return RsaUtils.privateEncrypt(data, genConfigMapper.getConfigByType().getPrivateKey());
    }
}
