package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenTemplateCollection;
import com.moonlit.generator.generator.entity.GenTemplateConfig;
import com.moonlit.generator.generator.entity.dto.GenTemplateCollectionDTO;
import com.moonlit.generator.generator.entity.vo.CollectionVO;
import com.moonlit.generator.generator.entity.vo.TemplateCollectionVO;
import com.moonlit.generator.generator.mapper.GenTemplateCollectionMapper;
import com.moonlit.generator.generator.service.GenTemplateCollectionService;
import com.moonlit.generator.generator.service.GenTemplateConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 模板组业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 28/6/2022 16:22
 * @email by.Moonlit@hotmail.com
 */
@Service
public class GenTemplateCollectionServiceImpl extends ServiceImpl<GenTemplateCollectionMapper, GenTemplateCollection> implements GenTemplateCollectionService {

    @Autowired
    private GenTemplateConfigService templateConfigService;

    /**
     * 分頁條件查詢模板组
     *
     * @param collectionDTO 查詢參數實體
     * @return 結果集合
     */
    @Override
    public PageResult<GenTemplateCollection> pageList(GenTemplateCollectionDTO collectionDTO) {
        LambdaQueryWrapper<GenTemplateCollection> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotEmpty(collectionDTO)) {
            if (ObjectUtil.isNotEmpty(collectionDTO.getName())) {
                queryWrapper.like(GenTemplateCollection::getName, collectionDTO.getName());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增模板组
     *
     * @param templateCollection 模板组實體
     * @return 結果
     */
    @Override
    public Boolean insertTemplateCollection(GenTemplateCollection templateCollection) {
        templateCollection.setCreateDate(LocalDateTime.now());
        return this.save(templateCollection);
    }

    /**
     * 修改模板组
     *
     * @param templateCollection 模板组實體
     * @return 結果
     */
    @Override
    public Boolean updateTemplateCollection(GenTemplateCollection templateCollection) {
        return this.updateById(templateCollection);
    }

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return 結果
     */
    @Override
    public Boolean deleteTemplateCollectionByIds(String ids) {
        return this.removeByIds(Arrays.asList(Convert.toStrArray(ids)));
    }

    /**
     * 获取所有的模板组名称
     *
     * @return 结果集合
     */
    @Override
    public ArrayList<CollectionVO> getCollectionName() {
        return baseMapper.getCollectionName();
    }

    /**
     * 根據模板組id獲取組内所有狀態正常的模板
     *
     * @param templateCollectionId 模板組id
     * @return 模板集合
     */
    @Override
    public List<GenTemplateConfig> getTemplateByCollectionId(Long templateCollectionId) {
        LambdaQueryWrapper<GenTemplateConfig> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(GenTemplateConfig::getCollectionId, templateCollectionId).eq(GenTemplateConfig::getState, true);
        return templateConfigService.list(queryWrapper);
    }

    /**
     * 獲取模板組與模板名稱
     *
     * @return 结果集
     */
    @Override
    public ArrayList<TemplateCollectionVO> getTemplateCollection() {
        return baseMapper.getTemplateCollection();
    }

}
