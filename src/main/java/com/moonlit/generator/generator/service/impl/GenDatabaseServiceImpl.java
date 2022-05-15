package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.constant.DatabaseConstant;
import com.moonlit.generator.common.encrypt.AesUtils;
import com.moonlit.generator.common.encrypt.RsaUtils;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenDatabase;
import com.moonlit.generator.generator.entity.GenSystemConfig;
import com.moonlit.generator.generator.entity.dto.GenDatabaseDTO;
import com.moonlit.generator.generator.mapper.GenDatabaseMapper;
import com.moonlit.generator.generator.mapper.GenSystemConfigMapper;
import com.moonlit.generator.generator.service.GenDatabaseService;
import com.moonlit.generator.system.entity.vo.DictVO;
import com.moonlit.generator.system.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 数据库配置业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:33
 * @email by.Moonlit@hotmail.com
 */
@Service
public class GenDatabaseServiceImpl extends ServiceImpl<GenDatabaseMapper, GenDatabase> implements GenDatabaseService {

    @Autowired
    private DictTypeService dictTypeService;

    @Autowired
    private GenSystemConfigMapper genSystemConfigMapper;

    /**
     * 条件分页查询
     *
     * @param genDatabaseDTO 表实体
     * @return 结果集
     */
    @Override
    public PageResult<GenDatabase> pageList(GenDatabaseDTO genDatabaseDTO) {
        LambdaQueryWrapper<GenDatabase> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotNull(genDatabaseDTO)) {
            if (ObjectUtil.isNotEmpty(genDatabaseDTO.getAddress())) {
                queryWrapper.eq(GenDatabase::getAddress, genDatabaseDTO.getAddress());
            }
            if (ObjectUtil.isNotEmpty(genDatabaseDTO.getName())) {
                queryWrapper.like(GenDatabase::getName, "%" + genDatabaseDTO.getName() + "%");
            }
        }
        queryWrapper.orderByDesc(GenDatabase::getCreateDate);
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增
     *
     * @param genDatabase 表实体
     * @return 结果
     */
    @Override
    public Boolean insertDatabase(GenDatabase genDatabase) {
        genDatabase.setUserName(encrypt(genDatabase.getUserName()));
        genDatabase.setPassword(encrypt(genDatabase.getPassword()));
        this.matchDriver(genDatabase);
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
    public Boolean updateDatabase(GenDatabase genDatabase) {
        GenDatabase db = this.getById(genDatabase.getId());

        // 校验用户名与密码是否修改过
        if (!db.getUserName().equals(genDatabase.getUserName())) {
            genDatabase.setUserName(encrypt(genDatabase.getUserName()));
        }
        if (!db.getPassword().equals(genDatabase.getPassword())) {
            genDatabase.setPassword(encrypt(genDatabase.getPassword()));
        }
        this.matchDriver(genDatabase);
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
    public Boolean deleteDatabaseByIds(String ids) {
        return this.removeByIds(Arrays.asList(Convert.toStrArray(ids)));
    }

    /**
     * 更新用戶名與密碼
     *
     * @param key 鹽
     */
    @Override
    public void updateDatabasesInData(String key) {
        // 獲取原來的
        GenSystemConfig systemConfig = genSystemConfigMapper.selectById(1);
        String originalKey = RsaUtils.publicDecrypt(systemConfig.getSalt(), systemConfig.getPublicKey());

        List<GenDatabase> list = this.list();
        for (GenDatabase genDatabase : list) {
            String userName = AesUtils.decryptBase64(genDatabase.getUserName(), originalKey);
            String password = AesUtils.decryptBase64(genDatabase.getPassword(), originalKey);
            // 重新加密用戶名稱與密碼
            genDatabase.setUserName(AesUtils.encryptBase64(userName, key));
            genDatabase.setPassword(AesUtils.encryptBase64(password, key));
            genDatabase.setUpdateDate(LocalDateTime.now());
        }
        this.updateBatchById(list);
    }

    /**
     * 獲取數據庫名
     *
     * @return 结果集
     */
    @Override
    public List<DictVO> dropDown() {
        ArrayList<DictVO> vos = new ArrayList<>();
        List<GenDatabase> list = this.list();
        list.forEach(genDatabase -> vos.add(new DictVO(genDatabase.getId().toString(), genDatabase.getName())));
        return vos;
    }

    /*---------------------------------------- 内部方法 ----------------------------------------*/

    /**
     * 匹配數據庫驅動
     *
     * @param genDatabase 表实体
     */
    private void matchDriver(GenDatabase genDatabase) {
        Collection<DictVO> vos = dictTypeService.dropDown("database_type");
        for (DictVO vo : vos) {
            if (genDatabase.getType().equals(Integer.parseInt(vo.getKey()))) {
                genDatabase.setDriverClassName(DatabaseConstant.getDriverClass(vo.getName()));
            }
        }
    }

    /**
     * RSA数据加密
     *
     * @param data 数据
     * @return 结果
     */
    private String encrypt(String data) {
        GenSystemConfig genSystemConfig = genSystemConfigMapper.selectById(1);
        String key = RsaUtils.publicDecrypt(genSystemConfig.getSalt(), genSystemConfig.getPublicKey());
        return AesUtils.encryptBase64(data, key);
    }
}
