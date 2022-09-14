package com.moonlit.generator.generator.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 键值映射实体
 *
 * @author Joshua
 * @version 1.0
 * @date 4/8/2022 14:09
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenFieldMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 键
     */
    private String comment;

    /**
     * 值
     */
    private String mapping;

    /**
     * 类型（0：String、1：Boolean、2、List）
     */
    private Integer type;

    /**
     * 状态（0 否 1 是）
     */
    private Boolean state;

}
