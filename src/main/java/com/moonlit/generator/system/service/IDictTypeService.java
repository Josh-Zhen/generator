package com.moonlit.generator.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.DictVO;
import com.moonlit.generator.system.entity.DictType;

import java.util.List;

/**
 * 字典业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
public interface IDictTypeService extends IService<DictType> {

    /**
     * 根据CODE查询字典所有值
     *
     * @param code 编号
     * @return 结果集
     */
    List<DictVO> dropDown(String code);

    /**
     * 分页查询字典管理
     *
     * @param dictType 字典管理
     * @return 字典管理集合
     */
    PageResult<DictType> pageList(DictType dictType);

    /**
     * 新增字典管理
     *
     * @param dictType 字典管理
     * @return 结果
     */
    Boolean insertDictType(DictType dictType);

    /**
     * 修改字典管理
     *
     * @param dictType 字典管理
     * @return 结果
     */
    Boolean updateDictType(DictType dictType);

    /**
     * 删除字典管理信息
     *
     * @param id 字典管理ID
     * @return 结果
     */
    Boolean deleteDictTypeById(Long id);

}