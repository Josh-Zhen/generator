package com.moonlit.generator.generator.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 作者配置查询实体
 *
 * @author Joshua
 * @version 1.0
 * @date 2022/2/10 16:38
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenAuthorConfigDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作者
     */
    private String author;

}
