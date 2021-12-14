package com.moonlit.generator.generator.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公、私鑰
 *
 * @author Joshua
 * @version 1.0
 * @date 13/12/2021 14:28
 * @email by.Moonlit@hotmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyPairVO {

    /**
     * 公鑰
     */
    private String publicKey;

    /**
     * 私鑰
     */
    private String privateKey;
}
