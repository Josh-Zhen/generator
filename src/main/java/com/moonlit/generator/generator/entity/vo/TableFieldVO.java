package com.moonlit.generator.generator.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 表字段實體
 *
 * @author Joshua
 * @version 1.0
 * @date 21/5/2022 0:26
 * @email by.Moonlit@hotmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableFieldVO implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 列名
     */
    private String columnName;

    /**
     * 是否為主鍵（0否，1是）
     */
    private Boolean columnKey;

    /**
     * 是否是空（0否，1是）
     */
    private Boolean isNullable;

    /**
     * 順序
     */
    private Integer ordinalPosition;

    /**
     * 備注
     */
    private String columnComment;

    /**
     * 是否自增（0否，1是）
     */
    private Boolean extra;

    /**
     * 數據類型
     */
    private String columnType;

}
