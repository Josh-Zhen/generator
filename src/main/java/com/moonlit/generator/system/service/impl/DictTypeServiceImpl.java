package com.moonlit.generator.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.common.exception.enums.SystemCodeEnum;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.DictVO;
import com.moonlit.generator.system.entity.DictType;
import com.moonlit.generator.system.mapper.DictTypeMapper;
import com.moonlit.generator.system.service.IDictDataService;
import com.moonlit.generator.system.service.IDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 字典业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
@Service
public class DictTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictType> implements IDictTypeService {

    @Autowired
    private IDictDataService dictDataService;

    /**
     * 根据CODE查询字典所有值
     *
     * @param code 编号
     * @return 结果集
     */
    @Override
    public List<DictVO> dropDown(String code) {
        LambdaQueryWrapper<DictType> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DictType::getCode, code);
        DictType one = this.getOne(queryWrapper);
        if (ObjectUtil.isNull(one)) {
            throw new BusinessException(SystemCodeEnum.DICT_TYPE_NOT_EXIST);
        }
        return dictDataService.getDictDataListByDictTypeId(one.getId());
    }

    /**
     * 分页查询字典管理
     *
     * @param dictType 字典管理
     * @return 字典管理集合
     */
    @Override
    public PageResult<DictType> pageList(DictType dictType) {
        LambdaQueryWrapper<DictType> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotNull(dictType)) {
            if (ObjectUtil.isNotEmpty(dictType.getName())) {
                queryWrapper.like(DictType::getName, dictType.getName());
            }
            if (ObjectUtil.isNotEmpty(dictType.getCode())) {
                queryWrapper.like(DictType::getCode, dictType.getCode());
            }
        }
        queryWrapper.orderByDesc(DictType::getCreateDate);
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增字典管理
     *
     * @param dictType 字典管理
     * @return 结果
     */
    @Override
    public Boolean insertDictType(DictType dictType) {
        this.checkCodeExists(dictType);
        dictType.setCreateDate(LocalDateTime.now());
        return this.save(dictType);
    }

    /**
     * 修改字典管理
     *
     * @param dictType 字典管理
     * @return 结果
     */
    @Override
    public Boolean updateDictType(DictType dictType) {
        this.checkCodeExists(dictType);
        dictType.setUpdateDate(LocalDateTime.now());
        return this.updateById(dictType);
    }

    /**
     * 删除字典管理信息
     *
     * @param id 字典管理ID
     * @return 结果
     */
    @Override
    public Boolean deleteDictTypeById(Long id) {
        return this.removeById(id);
    }

    /**
     * 校验是否存在相同编号
     *
     * @param dictType 参数
     */
    private void checkCodeExists(DictType dictType) {
        LambdaQueryWrapper<DictType> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(DictType::getCode, dictType.getCode());
        DictType one = this.getOne(queryWrapper);
        if (null != one && !dictType.getId().equals(one.getId())) {
            if (dictType.getCode().equals(one.getCode())) {
                throw new BusinessException(11013, "编号已存在");
            }
        }
    }
}