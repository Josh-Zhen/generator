package com.moonlit.generator.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 库查询临时实体
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/12 16:49
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenDbVo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 库名称
     */
    private String dbName;

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
