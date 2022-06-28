package com.moonlit.generator.generator.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 模板组實體
 *
 * @author Joshua
 * @version 1.0
 * @date 28/6/2022 16:15
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenTemplateCollection {

    /**
     * id
     */
    private Long id;

    /**
     * 模板組名稱
     */
    private String name;

    /**
     * 状态（0否、1是）
     */
    private Boolean state;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createDate;

}
