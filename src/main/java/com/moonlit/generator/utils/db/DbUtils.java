package com.moonlit.generator.utils.db;

import com.moonlit.generator.common.encrypt.RsaUtils;
import com.moonlit.generator.entity.GenDb;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
     * 根据库名获取表数据
     *
     * @param genDb     实体
     * @param publicKey 公钥
     * @throws SQLException 异常
     */
    public void connectDb(GenDb genDb, String publicKey) throws SQLException {
        Connection connection = null;
        try {
            //创建连接
            String dbName = genDb.getDbName();
            String userName = RsaUtils.publicDecrypt(genDb.getUserName(), publicKey);
            String password = RsaUtils.publicDecrypt(genDb.getPassword(), publicKey);

            //创建url-数据库为：INFORMATION_SCHEMA
            String url = "jdbc:mysql://" + genDb.getDbAddress() + ":" + genDb.getDbPort() + "/INFORMATION_SCHEMA?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
            //获取连接
            connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            // 根据库名筛选表
            String sql = "SELECT DISTINCT TABLE_NAME FROM `COLUMNS` WHERE TABLE_SCHEMA = " + dbName;
            //执行sql
            ResultSet resultSet = statement.executeQuery(sql);
            ArrayList<String> tables = new ArrayList<>();
//            //读取到List中
//            while (resultSet.next()) {
//                buf.add(resultSet.getString(1));
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}