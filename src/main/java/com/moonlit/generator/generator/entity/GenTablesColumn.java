package com.moonlit.generator.generator.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 库表生成_表字段明细实体
 *
 * @author Joshua
 * @date 2021-09-30
 */
@Data
public class GenTablesColumn implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    //-------------------- 數據庫字段 --------------------/

    /**
     * 表id
     */
    private Integer tableId;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 字段描述
     */
    private String columnComment;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 列类型
     */
    private String columnType;

    /**
     * 是否主键
     */
    private Integer isPrimaryKey;

    /**
     * 是否自增（0否，1是）
     */
    private String isIncrement;

    /**
     * 是否為空（0否，1是）
     */
    private String isRequired;

    //-------------------- 數據字段 --------------------/

    /**
     * JAVA类型
     */
    private String javaType;

    /**
     * JAVA字段名
     */
    private String javaField;

    /**
     * 是否为插入字段（0否，1是）
     */
    private String isInsert;

    /**
     * 是否编辑字段（0否，1是）
     */
    private String isEdit;

    /**
     * 是否列表字段（0否，1是）
     */
    private String isList;

    /**
     * 是否查询字段（0否，1是）
     */
    private String isQuery;

    /**
     * 查询方式（等于、不等于、大于、小于、范围）
     */
    private String queryType;

    /**
     * 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    private String htmlType;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime updateTime;
}
