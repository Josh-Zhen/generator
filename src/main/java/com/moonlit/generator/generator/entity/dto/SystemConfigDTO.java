package com.moonlit.generator.generator.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Joshua
 * @version 1.0
 * @date 29/5/2022 0:11
 * @email by.Moonlit@hotmail.com
 */
@Data
public class SystemConfigDTO implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 鹽
     */
    private String salt;

    /**
     * 鹽是否持久化(0:否 1:是)
     */
    private Boolean state;

}
