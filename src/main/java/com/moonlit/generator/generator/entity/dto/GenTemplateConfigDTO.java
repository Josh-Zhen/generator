package com.moonlit.generator.generator.entity.dto;

import lombok.Data;

/**
 * @author Joshua
 * @version 1.0
 * @date 31/5/2022 16:58
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenTemplateConfigDTO {

    private Long collectionId;

    /**
     * 名称
     */
    private String name;

    /**
     * 模板后缀名
     */
    private String suffixName;
}
