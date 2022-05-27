package com.moonlit.generator.generator.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 插入數據
 *
 * @author Joshua
 * @version 1.0
 * @date 22/5/2022 16:07
 * @email by.Moonlit@hotmail.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveTablesColumnDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库id
     */
    @NotNull(message = "数据库id不能为空")
    @ApiModelProperty(name = "databaseId", value = "数据库id")
    private Long databaseId;

    /**
     * 數據表名
     */
    @NotNull(message = "数据表名不能为空")
    @ApiModelProperty(name = "tableName", value = "数据表名")
    private String tableName;

    /**
     * 数据表id
     */
    @NotNull(message = "数据表id不能为空")
    @ApiModelProperty(name = "tableId", value = "数据表id")
    private Long tableId;
}
