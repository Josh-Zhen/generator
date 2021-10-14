package com.moonlit.generator.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 连接库实体
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:21
 * @email by.Moonlit@hotmail.com
 */
@Data
public class GenDb {

    /**
     * 主键
     */
    private Long id;

    /**
     * 库名称
     */
    private String dbName;

    /**
     * 数据库连接类（mysql：com.mysql.cj.jdbc.Driver）
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
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

}