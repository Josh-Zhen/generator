package com.moonlit.generator.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 库表生成_表实体
 *
 * @author Joshua
 * @date 2021-09-30
 */
@Data
@ApiModel(value = "GenTables", description = "库表生成_表实体")
public class GenTables implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 数据库名称
     */
    @ApiModelProperty(name = "dbName", value = "数据库名称")
    private String dbName;

    /**
     * 表名称
     */
    @ApiModelProperty(name = "tableName", value = "表名称")
    private String tableName;

    /**
     * 包路径
     */
    @ApiModelProperty(name = "packageName", value = "包路径")
    private String packageName;

    /**
     * 模块名
     */
    @ApiModelProperty(name = "moduleName", value = "模块名")
    private String moduleName;

    /**
     * 类名
     */
    @ApiModelProperty(name = "actTime", value = "激活时间")
    private String className;

    /**
     * 创建者
     */
    @ApiModelProperty(name = "createName", value = "创建者")
    private String createName;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createDate", value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    @ApiModelProperty(name = "updateDate", value = "更新时间")
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime updateDate;
}
