package com.moonlit.generator.generator.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 模板配置實體
 *
 * @author Joshua
 * @version 1.0
 * @date 31/5/2022 16:36
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenTemplateConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 模板
     */
    private String template;

    /**
     * 模板后缀名
     */
    private String suffixName;

    /**
     * 状态（0:否 1:是）
     */
    private Boolean state;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createDate;

}
