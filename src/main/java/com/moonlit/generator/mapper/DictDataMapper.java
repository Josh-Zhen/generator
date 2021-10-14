package com.moonlit.generator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moonlit.generator.entity.DictData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 字典小类Mapper层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
@Mapper
public interface DictDataMapper extends BaseMapper<DictData> {

    /**
     * 根据大类的code与小类值获取小类名称
     *
     * @param code  大类编号
     * @param value 小类编码
     * @return 小类名称
     */
    DictData getDictDataByCodeAndValue(String code, String value);

    /**
     * 根据code查询出字典数据
     *
     * @param code
     * @return
     */
    List<DictData> selectDataByCode(String code);
}