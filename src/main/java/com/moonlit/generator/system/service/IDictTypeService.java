package com.moonlit.generator.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
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

}