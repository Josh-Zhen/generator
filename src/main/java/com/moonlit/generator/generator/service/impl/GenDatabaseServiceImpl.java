package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.symmetric.AES;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.constants.DatabaseDriverConstant;
import com.moonlit.generator.generator.entity.GenDatabase;
import com.moonlit.generator.generator.entity.dto.GenDatabaseDTO;
import com.moonlit.generator.generator.mapper.GenDatabaseMapper;
import com.moonlit.generator.generator.service.GenDatabaseService;
import com.moonlit.generator.generator.service.GenSystemConfigService;
import com.moonlit.generator.system.entity.vo.DictVO;
import com.moonlit.generator.system.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
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
    private GenSystemConfigService systemConfigService;

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
                queryWrapper.like(GenDatabase::getName, genDatabaseDTO.getName());
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
        genDatabase.setUserName(this.encrypt(genDatabase.getUserName()));
        genDatabase.setPassword(this.encrypt(genDatabase.getPassword()));
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
        String encryptUserName = this.encrypt(genDatabase.getUserName());
        if (!db.getUserName().equals(encryptUserName)) {
            genDatabase.setUserName(encryptUserName);
        }
        String encryptPassword = this.encrypt(genDatabase.getPassword());
        if (!db.getPassword().equals(encryptPassword)) {
            genDatabase.setPassword(encryptPassword);
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
     * @param originalKey 原始密鑰
     * @param newKey      新密鑰
     */
    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public void updateDatabasesInData(String originalKey, String newKey) {
        List<GenDatabase> list = this.list();
        if (list.size() > 0) {
            for (GenDatabase database : list) {
                // 獲取原始數據
                AES aes = new AES(originalKey.getBytes(StandardCharsets.UTF_8));
                String userName = aes.decryptStr(database.getUserName());
                String password = aes.decryptStr(database.getPassword());
                // 重新加密用戶名稱與密碼
                aes = new AES(newKey.getBytes(StandardCharsets.UTF_8));
                database.setUserName(aes.encryptBase64(userName));
                database.setPassword(aes.encryptBase64(password));
                database.setUpdateDate(LocalDateTime.now());
            }
            this.updateBatchById(list);
        }
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
                genDatabase.setDriverClassName(DatabaseDriverConstant.getDriverClass(vo.getName()));
                return;
            }
        }
    }

    /**
     * 数据加密
     * AES加密數據，RSA加密AES的密鑰
     *
     * @param data 数据
     * @return 结果
     */
    private String encrypt(String data) {
        AES aes = new AES(systemConfigService.getRsaKey().getBytes(StandardCharsets.UTF_8));
        return aes.encryptBase64(data);
    }

}
