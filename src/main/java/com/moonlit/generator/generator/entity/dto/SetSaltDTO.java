package com.moonlit.generator.generator.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Joshua
 * @version 1.0
 * @date 27/5/2022 12:47
 * @email by.Moonlit@hotmail.com
 */
@Data
public class SetSaltDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 密鑰
     */
    private String salt;

}
