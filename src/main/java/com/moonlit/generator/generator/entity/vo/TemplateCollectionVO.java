package com.moonlit.generator.generator.entity.vo;

import lombok.Data;

/**
 * 模板組VO實體
 *
 * @author Joshua
 * @version 1.0
 * @date 6/9/2022 16:19
 * @email by.Moonlit@hotmail.com
 */
@Data
public class TemplateCollectionVO {

    /**
     * 主鍵
     */
    private Long id;

    /**
     * 組名
     */
    private String name;

    /**
     * 模板名稱 (以“、 ”隔開)
     */
    private String templateName;

}
