package com.moonlit.generator.generator.entity.vo;

import com.moonlit.generator.generator.entity.GenConfig;
import com.moonlit.generator.generator.entity.GenTables;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Joshua
 * @version 1.0
 * @date 2021/11/30 13:31
 * @email by.Moonlit@hotmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestVO {

    private GenConfig genConfig;

    private GenTables genTables;

}
