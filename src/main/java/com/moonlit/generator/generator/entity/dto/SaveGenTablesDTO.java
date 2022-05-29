package com.moonlit.generator.generator.entity.dto;

import com.moonlit.generator.generator.entity.vo.DatabaseTablesVO;
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
public class SaveGenTablesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据库id
     */
    @NotNull(message = "数据库id不能为空")
    private Long databaseId;

    /**
     * 數據集合
     */
    private ArrayList<DatabaseTablesVO> list;

    /**
     * 表配置id
     */
    @NotNull(message = "数据表配置id不能为空")
    private Long tableConfigId;
}
