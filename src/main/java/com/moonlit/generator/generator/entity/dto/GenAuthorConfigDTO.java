package com.moonlit.generator.generator.entity.dto;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "GenConfigDTO", description = "作者配置查询实体")
public class GenAuthorConfigDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作者
     */
    private String author;

}
