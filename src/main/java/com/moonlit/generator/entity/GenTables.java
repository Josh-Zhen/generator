package com.moonlit.generator.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 库表生成_表实体
 *
 * @author Joshua
 * @date 2021-09-30
 */
@Data
public class GenTables implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 包路径
     */
    private String packageName;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 类名
     */
    private String className;

    /**
     * 创建者
     */
    private String createName;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;
}
