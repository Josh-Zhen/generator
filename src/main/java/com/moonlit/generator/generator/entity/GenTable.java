package com.moonlit.generator.generator.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 數據表詳情实体
 *
 * @author Joshua
 * @date 2021-09-30
 */
@Data
@NoArgsConstructor
public class GenTable implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 数据库id
     */
    private Long databaseId;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableComment;

    /**
     * 对象名
     */
    private String objectName;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 功能名
     */
    private String functionName;

    /**
     * 配置表id
     */
    private Long configId;

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
     * 實體構造器
     *
     * @param databaseId   庫id
     * @param tableName    表名
     * @param tableComment 表描述
     * @param configId     配置表id
     */
    public GenTable(Long databaseId, String tableName, String tableComment, Long configId) {
        this.databaseId = databaseId;
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.configId = configId;
        this.createDate = LocalDateTime.now();
    }
}
