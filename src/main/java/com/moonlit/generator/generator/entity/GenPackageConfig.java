package com.moonlit.generator.generator.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 包配置实体
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:35
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenPackageConfig {

    /**
     * id
     */
    @ApiModelProperty(name = "id", value = "id")
    private Long id;

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
     * 创建时间
     */
    @ApiModelProperty(name = "createDate", value = "创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createDate;

}
