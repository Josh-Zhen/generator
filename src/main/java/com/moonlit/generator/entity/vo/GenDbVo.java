package com.moonlit.generator.entity.vo;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 库查询临时实体
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/12 16:49
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenDbVo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 库名称
     */
    private String dbName;

    /**
     * 创建者
     */
    private String createName;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime updateDate;

}
