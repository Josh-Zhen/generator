package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenTableColumn;
import com.moonlit.generator.generator.entity.dto.GenColumnDTO;
import com.moonlit.generator.generator.entity.dto.SaveTableColumnDTO;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 數據表字段詳情业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 8:52
 * @email by.Moonlit@hotmail.com
 */
public interface GenTableColumnService extends IService<GenTableColumn> {

    /**
     * 条件分页查询
     *
     * @param dto 查询实体
     * @return 结果集
     */
    PageResult<GenTableColumn> pageList(GenColumnDTO dto);

    /**
     * 新增/刷新
     *
     * @param saveDTO 实体
     * @return 结果
     */
    Boolean insertTableColumn(SaveTableColumnDTO saveDTO);

    /**
     * 修改
     *
     * @param genTableColumn 实体
     * @return 结果
     */
    Boolean updateTableColumn(GenTableColumn genTableColumn);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteTableColumnByIds(String ids);

    /**
     * 根據id集合獲取所有相關數據
     *
     * @param tablesId 表id集合
     * @return 字段id結果集
     */
    ArrayList<Long> listColumnsByTableId(Collection<String> tablesId);
}
