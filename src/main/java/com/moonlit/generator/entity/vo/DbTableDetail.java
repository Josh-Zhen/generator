package com.moonlit.generator.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 表明细实体（用于查询并添加表类型使用）
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:44
 * @email by.Moonlit@hotmail.com
 */
@Data
public class DbTableDetail {

    /**
     * 库名称
     */
    private String dbName;

    /**
     * 表名称
     */
    private String tableName;

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
