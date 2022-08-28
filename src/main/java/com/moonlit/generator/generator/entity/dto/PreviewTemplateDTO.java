package com.moonlit.generator.generator.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 預覽模板文件
 *
 * @author Joshua
 * @version 1.0
 * @date 5/7/2022 17:18
 * @email by.Moonlit@hotmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreviewTemplateDTO {

    /**
     * 模板名稱
     */
    private String name;

    /**
     * 内容
     */
    private String template;

}
