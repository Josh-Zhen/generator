package com.moonlit.generator.generator.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.moonlit.generator.generator.entity.vo.TableFieldVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 數據表字段詳情实体
 *
 * @author Joshua
 * @date 2021-09-30
 */
@Data
@NoArgsConstructor
public class GenTableColumn implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /*---------------------------------------- 數據庫字段 ----------------------------------------*/

    /**
     * 數據表id
     */
    private Long tableId;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 描述
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
     * 是否為主鍵（0否，1是）
     */
    private Boolean isPrimaryKey;

    /**
     * 是否自增（0否，1是）
     */
    private Boolean isIncrement;

    /**
     * 是否為空（0否，1是）
     */
    private Boolean isRequired;

    /*---------------------------------------- 數據字段 ----------------------------------------*/

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
     * 該字段是否可寫
     */
    private Boolean isInsert;

    /**
     * 是否编辑字段（0否，1是）
     * 用於在修改
     * 0：id(主鍵) 、創建時間、更新時間等後端設置值
     * 1：varchar、text（大部分String類型）、number類型
     */
    private Boolean isEdit;

    /**
     * 是否列表字段（0否，1是）
     */
    private Boolean isList;

    /**
     * 是否查询字段（0否，1是）
     * 用於條件查詢
     */
    private Boolean isQuery;

    /**
     * 查询方式（等于-eq、不等于-ne、大于-gt、小于-lt、范围-like）
     * 默認 eq
     */
    private String queryType;

    /*---------------------------------------- 前端字段 ----------------------------------------*/

    /**
     * 显示类型（文本框-input、文本域-text、下拉框-dropdown、复选框-checkbox、单选框-radio、日期控件-datetime）
     * 默認 input
     */
    private String htmlType;

    /**
     * 字典类型（前端字段字典）
     */
    private String dictType;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime updateDate;

    /**
     * 初始化構造器
     *
     * @param tableId 數據表id
     * @param vo      臨時實體
     */
    public GenTableColumn(Long tableId, TableFieldVO vo) {
        this.tableId = tableId;
        this.columnName = vo.getColumnName();
        this.columnComment = vo.getColumnComment();
        this.sort = vo.getOrdinalPosition();
        this.columnType = vo.getColumnType();
        this.isPrimaryKey = vo.getColumnKey();
        this.isIncrement = vo.getExtra();
        this.isRequired = vo.getIsNullable();
        this.createDate = LocalDateTime.now();
    }
}
