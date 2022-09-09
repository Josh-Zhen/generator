package com.moonlit.generator.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.generator.entity.GenTemplateCollection;
import com.moonlit.generator.generator.entity.GenTemplateConfig;
import com.moonlit.generator.generator.entity.dto.GenTemplateCollectionDTO;
import com.moonlit.generator.generator.entity.vo.CollectionVO;
import com.moonlit.generator.generator.entity.vo.TemplateCollectionVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板组业务层
 *
 * @author Joshua
 * @version 1.0
 * @date 28/6/2022 16:15
 * @email by.Moonlit@hotmail.com
 */
public interface GenTemplateCollectionService extends IService<GenTemplateCollection> {

    /**
     * 分頁條件查詢模板组
     *
     * @param collectionDTO 查詢參數實體
     * @return 結果集合
     */
    PageResult<GenTemplateCollection> pageList(GenTemplateCollectionDTO collectionDTO);

    /**
     * 新增模板组
     *
     * @param templateCollection 模板组實體
     * @return 結果
     */
    Boolean insertTemplateCollection(GenTemplateCollection templateCollection);

    /**
     * 修改模板组
     *
     * @param templateCollection 模板组實體
     * @return 結果
     */
    Boolean updateTemplateCollection(GenTemplateCollection templateCollection);

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return 結果
     */
    Boolean deleteTemplateCollectionByIds(String ids);

    /**
     * 获取所有的模板组名称
     *
     * @return 结果集合
     */
    ArrayList<CollectionVO> getCollectionName();

    /**
     * 根據模板組id獲取組内所有狀態正常的模板
     *
     * @param templateCollectionId 模板組id
     * @return 模板集合
     */
    List<GenTemplateConfig> getTemplateByCollectionId(Long templateCollectionId);

    /**
     * 獲取模板組與模板名稱
     *
     * @return 结果集
     */
    ArrayList<TemplateCollectionVO> getTemplateCollection();
}
