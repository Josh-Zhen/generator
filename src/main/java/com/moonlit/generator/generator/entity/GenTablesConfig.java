package com.moonlit.generator.generator.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 包配置实体
 * TODO 查詢時將其他屬性組成備注返回給前端
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:35
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenTablesConfig {

    /**
     * id
     */
    @ApiModelProperty(name = "id", value = "id")
    private Long id;

    /**
     * 配置名稱
     */
    @ApiModelProperty(name = "name", value = "配置名稱")
    private String name;

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
     * 表前綴
     */
    @ApiModelProperty(name = "tablePrefix", value = "表前綴")
    private String tablePrefix;

    /**
     * 移除表前綴(0 否 1 是)
     */
    @ApiModelProperty(name = "removePrefix", value = "移除表前綴(0 否 1 是)")
    private Boolean removePrefix;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createDate", value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createDate;

}
