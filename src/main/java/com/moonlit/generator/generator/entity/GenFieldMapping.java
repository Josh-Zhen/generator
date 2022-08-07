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
     * 状态状态（0停用、1正常、2無法修改）
     */
    private Integer status;
}
