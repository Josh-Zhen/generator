package com.moonlit.generator.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.response.DictVO;
import com.moonlit.generator.system.entity.DictData;

import java.util.List;

/**
 * 字典小类业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
public interface IDictDataService extends IService<DictData> {

    /**
     * 通过字典类型id查询字典name和value
     *
     * @param dictTypeId 主键id
     * @return 结果集
     */
    List<DictVO> getDictDataListByDictTypeId(Long dictTypeId);

    /**
     * 根据大类的code与小类值获取小类名称
     *
     * @param code  大类编号
     * @param value 小类编码
     * @return 小类名称
     */
    DictData getDictData(String code, String value);
}