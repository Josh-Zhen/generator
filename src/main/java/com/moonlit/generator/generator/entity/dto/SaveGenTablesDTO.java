package com.moonlit.generator.generator.entity.dto;

import com.moonlit.generator.generator.entity.vo.DatabaseTablesVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "数据库id不能为空")
    @ApiModelProperty(name = "databaseId", value = "数据库id")
    private Long databaseId;

    /**
     * 數據集合
     */
    @ApiModelProperty(name = "tableNames", value = "數據集合")
    private ArrayList<DatabaseTablesVO> list;

    /**
     * 表配置id
     */
    @NotNull(message = "数据表配置id不能为空")
    @ApiModelProperty(name = "tableConfigId", value = "表配置id")
    private Long tableConfigId;
}
