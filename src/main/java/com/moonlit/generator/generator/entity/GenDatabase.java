package com.moonlit.generator.generator.entity;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 數據库实体
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:21
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenDatabase {

    /**
     * 主键
     */
    private Long id;

    /**
     * 数据库地址
     */
    private String address;

    /**
     * 数据库端口
     */
    private String port;

    /**
     * 库名称
     */
    private String name;

    /**
     * 数据库类型
     */
    private Integer type;

    /**
     * 数据库驱动
     */
    private String driverClassName;

    /**
     * 用户名（私钥加密）
     */
    private String userName;

    /**
     * 密码（私钥加密）
     */
    private String password;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime updateDate;

}