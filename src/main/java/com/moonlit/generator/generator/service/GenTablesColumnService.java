package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.generator.entity.GenTablesColumn;

/**
 * 數據表字段明细业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 8:52
 * @email by.Moonlit@hotmail.com
 */
public interface GenTablesColumnService extends IService<GenTablesColumn> {

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return 对象
     */
    GenTablesColumn getGenTables(Integer id);

    /**
     * 新增
     *
     * @param genTablesColumn 实体
     * @return 结果
     */
    Boolean insertTablesColumn(GenTablesColumn genTablesColumn);

    /**
     * 修改
     *
     * @param genTablesColumn 实体
     * @return 结果
     */
    Boolean updateTablesColumn(GenTablesColumn genTablesColumn);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteTablesColumnByIds(String ids);

}
