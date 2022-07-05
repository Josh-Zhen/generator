package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenTemplateConfig;
import com.moonlit.generator.generator.entity.dto.GenTemplateConfigDTO;
import com.moonlit.generator.generator.entity.dto.PreviewTemplateDTO;
import com.moonlit.generator.generator.mapper.GenTemplateConfigMapper;
import com.moonlit.generator.generator.service.GenTableService;
import com.moonlit.generator.generator.service.GenTemplateConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 模板配置业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 31/5/2022 16:44
 * @email by.Moonlit@hotmail.com
 */
@Service
public class GenTemplateConfigServiceImpl extends ServiceImpl<GenTemplateConfigMapper, GenTemplateConfig> implements GenTemplateConfigService {

    @Autowired
    private GenTableService tableService;

    /**
     * 分頁條件查詢模板配置
     *
     * @param templateConfigDTO 查詢參數實體
     * @return 結果集合
     */
    @Override
    public PageResult<GenTemplateConfig> pageList(GenTemplateConfigDTO templateConfigDTO) {
        LambdaQueryWrapper<GenTemplateConfig> queryWrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotEmpty(templateConfigDTO)) {
            if (ObjectUtil.isNotEmpty(templateConfigDTO.getCollectionId())) {
                queryWrapper.like(GenTemplateConfig::getCollectionId, templateConfigDTO.getCollectionId());
            }
            if (ObjectUtil.isNotEmpty(templateConfigDTO.getName())) {
                queryWrapper.like(GenTemplateConfig::getName, templateConfigDTO.getName());
            }
            if (ObjectUtil.isNotEmpty(templateConfigDTO.getSuffixName())) {
                queryWrapper.like(GenTemplateConfig::getSuffixName, templateConfigDTO.getSuffixName());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    /**
     * 新增模板配置
     *
     * @param templateConfig 模板配置實體
     * @return 結果
     */
    @Override
    public Boolean insertTemplateConfig(GenTemplateConfig templateConfig) {
        templateConfig.setCreateDate(LocalDateTime.now());
        return this.save(templateConfig);
    }

    /**
     * 修改模板配置
     *
     * @param templateConfig 模板配置實體
     * @return 結果
     */
    @Override
    public Boolean updateTemplateConfig(GenTemplateConfig templateConfig) {
        return this.updateById(templateConfig);
    }

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return 結果
     */
    @Override
    public Boolean deleteTemplateConfigByIds(String ids) {
        return this.removeByIds(Arrays.asList(Convert.toStrArray(ids)));
    }

    /**
     * 根據id預覽代碼
     *
     * @param tableId 表id
     * @return 結果
     */
    @Override
    public PreviewTemplateDTO previewTemplateByTableId(Long tableId) {


        return null;
    }


}
