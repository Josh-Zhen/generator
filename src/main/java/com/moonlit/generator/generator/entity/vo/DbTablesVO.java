package com.moonlit.generator.generator.entity.vo;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 未添加的表名
 *
 * @author Joshua
 * @version 1.0
 * @date 7/12/2021 16:34
 * @email by.Moonlit@hotmail.com
 */
@Data
public class DbTablesVO {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 備注
     */
    private String note;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createDate;
    
}
