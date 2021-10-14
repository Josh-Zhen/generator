package com.moonlit.generator.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 作者相关配置实体
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:35
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenConfig {

    /**
     * id
     */
    private Long id;

    /**
     * 作者
     */
    private String author;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    private LocalDateTime updateDate;
}
