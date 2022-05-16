package com.moonlit.generator.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 字典实体类
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/7/5 14:45
 * @email by.Moonlit@hotmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictVO {

    /**
     * 鍵值
     */
    private String key;

    /**
     * 名稱
     */
    private String name;

}
