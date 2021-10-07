package com.moonlit.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.entity.GenTables;
import com.moonlit.generator.entity.GenTablesColumn;

/**
 * 库表生成_表字段明细业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 8:52
 * @email by.Moonlit@hotmail.com
 */
public interface GenTablesColumnService extends IService<GenTablesColumn> {

    /**
     * 分页查询【请填写功能名称】
     *
     * @param genTablesColumn 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    PageResult<GenTables> pageList(GenTablesColumn genTablesColumn);

    /**
     * 新增【请填写功能名称】
     *
     * @param genTablesColumn 【请填写功能名称】
     * @return 结果
     */
    Boolean insertTablesColumn(GenTablesColumn genTablesColumn);

    /**
     * 修改【请填写功能名称】
     *
     * @param genTablesColumn 【请填写功能名称】
     * @return 结果
     */
    Boolean updateTablesColumn(GenTablesColumn genTablesColumn);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteTablesColumnByIds(String ids);

}
