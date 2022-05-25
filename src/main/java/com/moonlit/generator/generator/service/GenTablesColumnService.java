package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenTablesColumn;
import com.moonlit.generator.generator.entity.dto.GenColumnDTO;
import com.moonlit.generator.generator.entity.dto.SaveTablesColumnDTO;

/**
 * 數據表字段詳情业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 8:52
 * @email by.Moonlit@hotmail.com
 */
public interface GenTablesColumnService extends IService<GenTablesColumn> {

    /**
     * 条件分页查询
     *
     * @param dto 查询实体
     * @return 结果集
     */
    PageResult<GenTablesColumn> pageList(GenColumnDTO dto);

    /**
     * 新增/刷新
     *
     * @param saveDTO 实体
     * @return 结果
     */
    Boolean insertTablesColumn(SaveTablesColumnDTO saveDTO);

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
