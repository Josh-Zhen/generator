package com.moonlit.generator.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.system.entity.DictData;
import com.moonlit.generator.system.entity.vo.DictVO;
import com.moonlit.generator.system.error.SystemCodeEnum;
import com.moonlit.generator.system.mapper.DictDataMapper;
import com.moonlit.generator.system.service.DictDataService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
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
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements DictDataService {

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
        queryWrapper.eq(DictData::getTypeId, dictTypeId).eq(DictData::getStatus, true).orderByAsc(DictData::getSort);
        //查询dictTypeId下所有的字典项
        List<DictData> results = this.list(queryWrapper);

        //抽取code和value封装到map返回
        List<DictVO> dictList = new ArrayList<>();
        results.forEach(dictData -> dictList.add(new DictVO(dictData.getValue(), dictData.getName())));
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

    /**
     * 分页查询字典管理(以父id查询)
     *
     * @param dictData 字典管理
     * @return 字典管理集合
     */
    @Override
    public PageResult<DictData> pageList(DictData dictData) {
        LambdaQueryWrapper<DictData> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotEmpty(dictData)) {
            if (ObjectUtil.isNotEmpty(dictData.getTypeId())) {
                queryWrapper.eq(DictData::getTypeId, dictData.getTypeId());
            }
            if (ObjectUtil.isNotEmpty(dictData.getName())) {
                queryWrapper.eq(DictData::getName, dictData.getName());
            }
            if (ObjectUtil.isNotEmpty(dictData.getValue())) {
                queryWrapper.eq(DictData::getValue, dictData.getValue());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增字典管理
     *
     * @param dictData 字典管理
     * @return 结果
     */
    @Override
    public Boolean insertDictData(DictData dictData) {
        this.checkValueExists(dictData);
        dictData.setCreateDate(LocalDateTime.now());
        return this.save(dictData);
    }

    /**
     * 修改字典管理
     *
     * @param dictData 字典管理
     * @return 结果
     */
    @Override
    public Boolean updateDictData(DictData dictData) {
        this.checkValueExists(dictData);
        dictData.setUpdateDate(LocalDateTime.now());
        return this.updateById(dictData);
    }

    /**
     * 校验是否存在相同名称或键
     *
     * @param dictData 参数
     */
    private void checkValueExists(DictData dictData) {
        LambdaQueryWrapper<DictData> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DictData::getTypeId, dictData.getTypeId())
                .and(wq -> wq.eq(DictData::getName, dictData.getName())
                        .or()
                        .eq(DictData::getValue, dictData.getValue()));
        Collection<DictData> list = this.list(queryWrapper);
        if (list.size() > 0) {
            for (DictData data : list) {
                if (dictData.getName().equals(data.getName()) && !dictData.getId().equals(data.getId())) {
                    throw new BusinessException(SystemCodeEnum.NAME_ALREADY_EXISTS);
                }
                if (dictData.getValue().equals(data.getValue()) && !dictData.getId().equals(data.getId())) {
                    throw new BusinessException(SystemCodeEnum.VALUE_ALREADY_EXISTS);
                }
            }
        }
    }
}