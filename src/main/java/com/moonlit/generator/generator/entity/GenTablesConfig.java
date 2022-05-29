package com.moonlit.generator.generator.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 表配置实体
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:35
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenTablesConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 配置名稱
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 表前綴
     */
    private String tablePrefix;

    /**
     * 移除表前綴(0 否 1 是)
     */
    private Boolean removePrefix;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createDate;

}
