package com.moonlit.generator.generator.entity.vo;

import lombok.Data;

/**
 * 未添加的表名
 *
 * @author Joshua
 * @version 1.0
 * @date 7/12/2021 16:34
 * @email by.Moonlit@hotmail.com
 */
@Data
public class DatabaseTablesVO {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表描述
     */
    private String tableComment;

}
