package com.moonlit.generator.generator.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Joshua
 * @version 1.0
 * @date 27/5/2022 0:24
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenTablesConfigDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配置名稱
     */
    @ApiModelProperty(name = "name", value = "配置名稱")
    private String name;

    /**
     * 作者
     */
    @ApiModelProperty(name = "author", value = "作者")
    private String author;
}
