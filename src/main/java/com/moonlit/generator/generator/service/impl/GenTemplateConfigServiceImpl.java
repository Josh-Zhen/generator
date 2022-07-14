package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.constant.CharacterConstant;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.utils.FilesUtils;
import com.moonlit.generator.common.utils.FreemarkerUtils;
import com.moonlit.generator.common.utils.TemplateUtils;
import com.moonlit.generator.generator.constants.error.TableConfigErrorCode;
import com.moonlit.generator.generator.entity.GenTable;
import com.moonlit.generator.generator.entity.GenTableConfig;
import com.moonlit.generator.generator.entity.GenTemplateConfig;
import com.moonlit.generator.generator.entity.dto.GenTemplateConfigDTO;
import com.moonlit.generator.generator.entity.dto.PreviewTemplateDTO;
import com.moonlit.generator.generator.mapper.GenTemplateConfigMapper;
import com.moonlit.generator.generator.service.GenTableConfigService;
import com.moonlit.generator.generator.service.GenTableService;
import com.moonlit.generator.generator.service.GenTemplateConfigService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
    @Autowired
    private GenTableConfigService configService;


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
    public ArrayList<PreviewTemplateDTO> previewTemplateByTableId(Long tableId) {
        ArrayList<PreviewTemplateDTO> list = new ArrayList<>();
        GenTableConfig tableConfig = configService.getById(1L);
        if (ObjectUtil.isEmpty(tableConfig)) {
            throw new BusinessException(TableConfigErrorCode.DEFAULT_AUTHOR_CONFIGURATION_NOT_FOUND);
        }

        GenTable table = tableService.getById(tableId);
        HashMap<String, String> condition = FreemarkerUtils.fillingCondition(table, tableConfig);

        for (String templateName : createTemplateFile()) {
            try {
                Template template = FreemarkerUtils.load(templateName);
                StringWriter stringWriter = new StringWriter();
                // 寫模板
                template.process(condition, stringWriter);
                // 將生成的模板存放進集合中
                list.add(new PreviewTemplateDTO(templateName, stringWriter.toString()));
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 創建模板文件
     *
     * @return 文件名稱
     */
    private ArrayList<String> createTemplateFile() {
        // 模板文件名稱
        ArrayList<String> fileNames = new ArrayList<>();

        //獲取需要展示的模板
        LambdaQueryWrapper<GenTemplateConfig> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(GenTemplateConfig::getDisplay, true);
        for (GenTemplateConfig templateConfig : this.list(queryWrapper)) {
            // 處理富文本數據
            String templateData = TemplateUtils.formatText(templateConfig.getTemplate());
            // 文件名稱 (格式：模板組編號-模板名稱.模板後綴名)
            String fileName = templateConfig.getCollectionId() + CharacterConstant.HYPHEN + templateConfig.getName()
                    + CharacterConstant.PERIOD + templateConfig.getSuffixName();
            // 生成模板
            FilesUtils.createTemplateFile(templateData, fileName);
            // 保存模板文件名
            fileNames.add(fileName);
        }
        return fileNames;
    }


}
