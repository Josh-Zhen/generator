package com.moonlit.generator.generator.entity.dto;

import lombok.Data;

/**
 * 刪除模板實體
 *
 * @author Joshua
 * @version 1.0
 * @date 15/9/2022 0:04
 * @email by.Moonlit@hotmail.com
 */
@Data
public class DeleteTemplateConfigDTO {

    /**
     * 模板組id
     */
    private Long collectionId;

    /**
     * 模板id集合
     */
    private String ids;

}
