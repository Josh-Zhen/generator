package com.moonlit.generator.generator.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moonlit.generator.common.constant.CharacterConstant;
import com.moonlit.generator.common.page.PageFactory;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.utils.FilesUtils;
import com.moonlit.generator.common.utils.FreemarkerUtils;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
        TableConfigAndDataAndColumnsBO tableData = tableService.getTableData(tableId, 1L);

        //獲取需要展示的模板
        LambdaQueryWrapper<GenTemplateConfig> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(GenTemplateConfig::getDisplay, true).eq(GenTemplateConfig::getState, true);
        List<GenTemplateConfig> templateList = this.list(queryWrapper);

        return constructTemplate(false, tableData, templateList, null);
    }

    /**
     * 生成代碼
     *
     * @param tableId       表id
     * @param tableConfigId 配置id
     * @param templates     模板數據
     * @return 數據
     */
    @Override
    public byte[] exportTemplate(Long tableId, Long tableConfigId, List<GenTemplateConfig> templates) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 構建一個zip文件數據流
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        // 構建需要導出的數據
        TableConfigAndDataAndColumnsBO tableData = tableService.getTableData(tableId, tableConfigId);
        constructTemplate(true, tableData, templates, zip);
        return outputStream.toByteArray();
    }

    /*---------------------------------------- 内部方法 ----------------------------------------*/

    /**
     * 構建模板
     *
     * @param previewOrExport 預覽:false 生成:true
     * @param tableData       表數據
     * @param templateList    需要展示的模板
     * @param zip             文件輸出流
     * @return 結果集合
     */
    private ArrayList<PreviewTemplateDTO> constructTemplate(Boolean previewOrExport, TableConfigAndDataAndColumnsBO tableData, List<GenTemplateConfig> templateList, ZipOutputStream zip) {
        ArrayList<PreviewTemplateDTO> list = new ArrayList<>();
        // 構建模板填充字段
        FreemarkerConditionBO condition = FreemarkerUtils.buildCondition(tableData);

        log.info("------------------ 模板生成中！ ------------------");
        for (String templateName : FreemarkerUtils.createTemplateFile(templateList)) {
            try {
                Template template = FreemarkerUtils.load(templateName);
                StringWriter stringWriter = new StringWriter();
                // 寫模板
                template.process(condition, stringWriter);

                // 模板文件名
                String fileName = templateName.split(CharacterConstant.HYPHEN)[1];
                // 生成代碼
                if (previewOrExport) {
                    // 添加到zip
                    zip.putNextEntry(new ZipEntry(FilesUtils.getFileName(condition, fileName)));
                    IoUtil.writeUtf8(zip, true, stringWriter.toString());
                    zip.closeEntry();
                } else {
                    // 預覽代碼
                    // 將生成的模板存放進集合中
                    list.add(new PreviewTemplateDTO(fileName, stringWriter.toString()));
                }
            } catch (IOException | TemplateException e) {
                // TODO 補異常
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
        return list;
    }

}
