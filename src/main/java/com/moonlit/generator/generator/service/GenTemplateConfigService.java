package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenTemplateConfig;
import com.moonlit.generator.generator.entity.dto.DeleteTemplateConfigDTO;
import com.moonlit.generator.generator.entity.dto.GenTemplateConfigDTO;
import com.moonlit.generator.generator.entity.dto.PreviewTemplateDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板配置业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 31/5/2022 16:43
 * @email by.Moonlit@hotmail.com
 */
public interface GenTemplateConfigService extends IService<GenTemplateConfig> {

    /**
     * 分頁條件查詢模板配置
     *
     * @param templateConfigDTO 查詢參數實體
     * @return 結果集合
     */
    PageResult<GenTemplateConfig> pageList(GenTemplateConfigDTO templateConfigDTO);

    /**
     * 新增模板配置
     *
     * @param templateConfig 模板配置實體
     * @return 結果
     */
    Boolean insertTemplateConfig(GenTemplateConfig templateConfig);

    /**
     * 修改模板配置
     *
     * @param templateConfig 模板配置實體
     * @return 結果
     */
    Boolean updateTemplateConfig(GenTemplateConfig templateConfig);

    /**
     * 批量删除
     *
     * @param dto 對象
     * @return 結果
     */
    Boolean deleteTemplateConfigByIds(DeleteTemplateConfigDTO dto);

    /**
     * 根據id預覽代碼
     *
     * @param tableId 表id
     * @return 結果
     */
    ArrayList<PreviewTemplateDTO> previewTemplateByTableId(Long tableId);

    /**
     * 生成代碼
     *
     * @param tableIds      表id集合
     * @param tableConfigId 配置id
     * @param templates     模板數據
     * @return 數據
     */
    byte[] exportTemplate(ArrayList<Long> tableIds, Long tableConfigId, List<GenTemplateConfig> templates);

}
