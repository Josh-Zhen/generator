package com.moonlit.generator.generator.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 新增數據表實體
 *
 * @author Joshua
 * @version 1.0
 * @date 2022/2/10 21:07
 * @email by.Moonlit@hotmail.com
 */
@Data
@ApiModel(value = "SaveGenTablesDTO", description = "新增數據表實體")
public class SaveGenTablesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库id
     */
    @ApiModelProperty(name = "databaseId", value = "数据库id")
    private Long databaseId;

    /**
     * 表名
     */
    @ApiModelProperty(name = "tableNames", value = "表名")
    private ArrayList<String> tableNames;

    /**
     * 移除前綴
     */
    @ApiModelProperty(name = "removePrefix", value = "移除前綴")
    private Boolean removePrefix;
}
