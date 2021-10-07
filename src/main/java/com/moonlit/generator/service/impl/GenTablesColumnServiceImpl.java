package com.moonlit.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.entity.GenTables;
import com.moonlit.generator.entity.GenTablesColumn;
import com.moonlit.generator.mapper.GenTablesColumnMapper;
import com.moonlit.generator.service.GenTablesColumnService;
import org.springframework.stereotype.Service;

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

    @Override
    public PageResult<GenTables> pageList(GenTablesColumn genTablesColumn) {
        return null;
    }

    @Override
    public Boolean insertTablesColumn(GenTablesColumn genTablesColumn) {
        return null;

    }

    @Override
    public Boolean updateTablesColumn(GenTablesColumn genTablesColumn) {
        return null;

    }

    @Override
    public Boolean deleteTablesColumnByIds(String ids) {
        return null;

    }
}
