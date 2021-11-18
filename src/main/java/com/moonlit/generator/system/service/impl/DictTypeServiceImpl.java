package com.moonlit.generator.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.common.exception.enums.SystemCodeEnum;
import com.moonlit.generator.common.response.DictVO;
import com.moonlit.generator.system.entity.DictType;
import com.moonlit.generator.system.mapper.DictTypeMapper;
import com.moonlit.generator.system.service.IDictDataService;
import com.moonlit.generator.system.service.IDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}