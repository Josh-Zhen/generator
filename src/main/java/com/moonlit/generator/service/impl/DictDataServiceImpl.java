package com.moonlit.generator.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.response.DictVO;
import com.moonlit.generator.entity.DictData;
import com.moonlit.generator.mapper.DictDataMapper;
import com.moonlit.generator.service.IDictDataService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典小类业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
@Service
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements IDictDataService {

    /**
     * 通过字典类型id查询字典name和value
     *
     * @param dictTypeId 主键id
     * @return 结果集
     */
    @Override
    public List<DictVO> getDictDataListByDictTypeId(Long dictTypeId) {
        //构造查询条件
        LambdaQueryWrapper<DictData> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DictData::getTypeId, dictTypeId).eq(DictData::getStatus, 1);
        //根据排序升序排列，序号越小越在前
        queryWrapper.orderByAsc(DictData::getSort);
        //查询dictTypeId下所有的字典项
        List<DictData> results = this.list(queryWrapper);

        //抽取code和value封装到map返回
        List<DictVO> dictList = CollectionUtil.newArrayList();
        results.forEach(dictData -> {
            DictVO dictVO = new DictVO();
            dictVO.setName(dictData.getName());
            dictVO.setValue(dictData.getValue());
            dictList.add(dictVO);
        });
        return dictList;
    }

    /**
     * 根据大类的code与小类值获取小类名称
     *
     * @param code  大类编号
     * @param value 小类编码
     * @return 小类名称
     */
    @Override
    public DictData getDictData(String code, String value) {
        return this.baseMapper.getDictDataByCodeAndValue(code, value);
    }
}