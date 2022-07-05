package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenTable;
import com.moonlit.generator.generator.entity.dto.GenTableDTO;
import com.moonlit.generator.generator.entity.dto.SaveGenTableDTO;
import com.moonlit.generator.generator.entity.vo.DatabaseTablesVO;

import java.util.List;

/**
 * 數據表配置业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:18
 * @email by.Moonlit@hotmail.com
 */
public interface GenTableService extends IService<GenTable> {

    /**
     * 条件分页查询
     *
     * @param genTableDTO 查询实体
     * @return 结果集
     */
    PageResult<GenTable> pageList(GenTableDTO genTableDTO);

    /**
     * 新增
     *
     * @param genTablesDTO 表实体
     * @return 结果
     */
    Boolean insertTable(SaveGenTableDTO genTablesDTO);

    /**
     * 修改
     *
     * @param tables 表实体
     * @return 结果
     */
    Boolean updateTable(GenTable tables);

    /**
     * 批量删除
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    Boolean deleteTableByIds(String ids);

    /**
     * 獲取未添加的表
     *
     * @param databaseId 主键
     * @return 结果
     */
    List<DatabaseTablesVO> list(Long databaseId);
}
