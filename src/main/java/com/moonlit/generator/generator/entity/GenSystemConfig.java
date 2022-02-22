package com.moonlit.generator.generator.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

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
public class GenSystemConfig {

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
