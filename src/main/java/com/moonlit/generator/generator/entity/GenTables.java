package com.moonlit.generator.generator.entity;

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
     * 数据库id
     */
    @ApiModelProperty(name = "databaseId", value = "数据库id")
    private Long databaseId;

    /**
     * 表名称
     */
    @ApiModelProperty(name = "tableName", value = "表名称")
    private String tableName;

    /**
     * 表描述
     */
    @ApiModelProperty(name = "tableComment", value = "表描述")
    private String tableComment;

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
    @ApiModelProperty(name = "className", value = "类名")
    private String className;

    /**
     * 业务名
     */
    @ApiModelProperty(name = "businessName", value = "业务名")
    private String businessName;

    /**
     * 类作者
     */
    @ApiModelProperty(name = "classAuthor", value = "类作者")
    private String classAuthor;

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
