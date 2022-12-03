package com.moonlit.generator.generator.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 數據表字段分頁條件條件實體
 *
 * @author Joshua
 * @version 1.0
 * @date 22/5/2022 15:39
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenColumnDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 數據表id
     */
    @NotNull(message = "数据表id不能为空")
    private Integer tableId;

    /**
     * 字段名
     */
    private String columnName;

    /**
     * 字段描述
     */
    private String columnComment;
}
