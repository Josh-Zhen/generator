package com.moonlit.generator.generator.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 数据表查询实体
 *
 * @author Joshua
 * @version 1.0
 * @date 2022/2/9 17:29
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenTablesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
}
