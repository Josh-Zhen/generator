package com.moonlit.generator.common.utils.db;

import com.moonlit.generator.common.encrypt.RsaUtils;
import com.moonlit.generator.generator.entity.GenDb;

import java.sql.*;
import java.util.ArrayList;

/**
 * 库工具类
 * <p>
 * 该类用于动态连接查询库表内容
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:59
 * @email by.Moonlit@hotmail.com
 */
public class DbUtils {

    /**
     * 连接Mysql数据库
     * <p>
     * 根据库名获取表数据
     *
     * @param genDb     实体
     * @param publicKey 公钥
     */
    public static ArrayList<String> connectMySqlDb(GenDb genDb, String publicKey) {
        Statement statement = null;
        Connection connection = null;
        ArrayList<String> tables = new ArrayList<>();

        //数据库连接方式
        String driverName = genDb.getDriverClassName();

        String dbName = genDb.getDbName();
        String userName = RsaUtils.publicDecrypt(genDb.getUserName(), publicKey);
        String password = RsaUtils.publicDecrypt(genDb.getPassword(), publicKey);
        try {
            // 注册驱动
            Class.forName(driverName);
            //创建url-数据库为：INFORMATION_SCHEMA
            String url = "jdbc:mysql://" + genDb.getDbAddress() + ":" + genDb.getDbPort() + "/INFORMATION_SCHEMA?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
            //获取连接
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
            // 根据库名筛选表
            String sql = "SELECT DISTINCT TABLE_NAME FROM `COLUMNS` WHERE TABLE_SCHEMA = '" + dbName + "'";
            //执行sql
            ResultSet resultSet = statement.executeQuery(sql);
            //读取到List中
            while (resultSet.next()) {
                // 存放数据表
                tables.add(resultSet.getString("TABLE_NAME"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tables;
    }
}