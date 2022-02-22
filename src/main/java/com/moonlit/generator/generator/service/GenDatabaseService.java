package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenDatabase;
import com.moonlit.generator.generator.entity.dto.GenDatabaseDTO;

/**
 * 数据库配置业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:28
 * @email by.Moonlit@hotmail.com
 */
public interface GenDatabaseService extends IService<GenDatabase> {

    /**
     * 条件分页查询
     *
     * @param genDatabaseDTO 表实体
     * @return 结果集
     */
    PageResult<GenDatabase> pageList(GenDatabaseDTO genDatabaseDTO);

    /**
     * 新增
     *
     * @param genDatabase 表实体
     * @return 结果
     */
    Boolean insertDatabase(GenDatabase genDatabase);

    /**
     * 修改
     *
     * @param genDatabase 表实体
     * @return 结果
     */
    Boolean updateDatabase(GenDatabase genDatabase);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteDatabaseByIds(String ids);

    /**
     * 更新用戶名與密碼
     *
     * @param publicKey  公鑰
     * @param key       鹽
     */
    void updateDatabasesInData(String publicKey, String key);
}
