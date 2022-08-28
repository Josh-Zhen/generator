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
import com.moonlit.generator.generator.constants.error.TemplateErrorCode;
import com.moonlit.generator.generator.entity.GenTemplateConfig;
import com.moonlit.generator.generator.entity.bo.FreemarkerConditionBO;
import com.moonlit.generator.generator.entity.bo.TableConfigAndDataAndColumnsBO;
import com.moonlit.generator.generator.entity.dto.GenTemplateConfigDTO;
import com.moonlit.generator.generator.entity.dto.PreviewTemplateDTO;
import com.moonlit.generator.generator.mapper.GenTemplateConfigMapper;
import com.moonlit.generator.generator.service.GenTableService;
import com.moonlit.generator.generator.service.GenTemplateConfigService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * 模板配置业务实现层
 *
 * @author Joshua
 * @version 1.0
 * @date 31/5/2022 16:44
 * @email by.Moonlit@hotmail.com
 */
@Slf4j
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
    public ArrayList<PreviewTemplateDTO> previewTemplateByTableId(Long tableId) {
        ArrayList<PreviewTemplateDTO> list = new ArrayList<>();
        TableConfigAndDataAndColumnsBO tableData = tableService.getTableData(tableId, 1L);

        // 構建模板填充字段
        FreemarkerConditionBO condition = FreemarkerUtils.buildCondition(tableData);

        log.info("------------------ 模板生成中！ ------------------");
        for (String templateName : createTemplateFile()) {
            try {
                Template template = FreemarkerUtils.load(templateName);
                StringWriter stringWriter = new StringWriter();
                // 寫模板
                template.process(condition, stringWriter);
                // 將生成的模板存放進集合中
                list.add(new PreviewTemplateDTO(templateName.split(CharacterConstant.HYPHEN)[1], stringWriter.toString()));
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /*---------------------------------------- 内部方法 ----------------------------------------*/

    /**
     * 創建模板文件
     *
     * @return 文件名稱
     */
    private ArrayList<String> createTemplateFile() {
        // 模板文件名稱
        ArrayList<String> fileNames = new ArrayList<>();
        // 初始化文件夾
        FilesUtils.initializationFolder();

        //獲取需要展示的模板
        LambdaQueryWrapper<GenTemplateConfig> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(GenTemplateConfig::getDisplay, true);
        Collection<GenTemplateConfig> list = this.list(queryWrapper);
        // 模板不存在
        if (list.size() == 0) {
            throw new BusinessException(TemplateErrorCode.TEMPLATE_DOES_NOT_EXIST);
        }
        // 循環生成模板
        for (GenTemplateConfig templateConfig : list) {
            // 處理富文本數據
            String templateData = TemplateUtils.formatText(templateConfig.getTemplate());
            // 文件名稱 (格式：模板組編號-模板名稱.模板後綴名)
            String fileName = templateConfig.getCollectionId() + CharacterConstant.HYPHEN + templateConfig.getName()
                    + CharacterConstant.PERIOD + templateConfig.getSuffixName();
            // 生成模板
            FilesUtils.createTemplateFile(fileName, templateData);
            // 保存模板文件名
            fileNames.add(fileName);
        }
        return fileNames;
    }

}
