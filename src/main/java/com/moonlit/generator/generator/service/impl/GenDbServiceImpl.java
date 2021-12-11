package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.encrypt.RsaUtils;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenDb;
import com.moonlit.generator.generator.mapper.GenConfigMapper;
import com.moonlit.generator.generator.mapper.GenDbMapper;
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
public class GenDbServiceImpl extends ServiceImpl<GenDbMapper, GenDb> implements GenDbService {

    @Autowired
    private GenConfigMapper genConfigMapper;
    @Autowired
    private GenTablesService genTablesService;


    /**
     * 条件分页查询
     *
     * @param genDb 表实体
     * @return 结果集
     */
    @Override
    public PageResult<GenDb> pageList(GenDb genDb) {
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
        genDb.setUserName(encrypt(genDb.getUserName()));
        genDb.setPassword(encrypt(genDb.getPassword()));
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
        GenDb db = this.getById(genDb.getId());

        // 校验用户名与密码是否修改过
        if (!db.getUserName().equals(genDb.getUserName())) {
            genDb.setUserName(encrypt(genDb.getUserName()));
        }
        if (!db.getPassword().equals(genDb.getPassword())) {
            genDb.setPassword(encrypt(genDb.getPassword()));
        }
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
