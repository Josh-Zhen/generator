package com.moonlit.generator.generator.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Joshua
 * @version 1.0
 * @date 27/5/2022 0:24
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenTableConfigDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配置名稱
     */
    private String name;

    /**
     * 作者
     */
    private String author;
}
