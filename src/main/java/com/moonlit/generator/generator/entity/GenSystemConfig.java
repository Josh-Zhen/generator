package com.moonlit.generator.generator.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系統配置实体
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:35
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenSystemConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 鹽
     */
    private String salt;

    /**
     * 鹽是否持久化(0:否 1:是)
     */
    private Boolean state;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime updateDate;
}
