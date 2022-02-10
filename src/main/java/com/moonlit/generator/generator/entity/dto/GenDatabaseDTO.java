package com.moonlit.generator.generator.entity.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 數據库配置查询实体
 *
 * @author Joshua
 * @version 1.0
 * @date 2022/2/9 17:38
 * @email by.Moonlit@hotmail.com
 */
@Data
@ApiModel(value = "GenDatabaseDTO", description = "數據库配置查询实体")
public class GenDatabaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库地址
     */
    private String dbAddress;

    /**
     * 库名称
     */
    private String dbName;


}
