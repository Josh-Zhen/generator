package com.moonlit.generator.service.impl;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.entity.vo.DbTableDetail;
import org.springframework.stereotype.Service;

/**
 * 数据库表业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:57
 * @email by.Moonlit@hotmail.com
 */
@Service
public class DbServiceImpl implements DbService {

    /**
     * 获取库表详情
     *
     * @param dbTableDetail 查询实体
     * @return 结果集
     */
    @Override
    public PageResult<DbTableDetail> getDbList(DbTableDetail dbTableDetail) {



        return null;
    }

}
