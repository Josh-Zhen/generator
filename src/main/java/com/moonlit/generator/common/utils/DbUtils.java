package com.moonlit.generator.common.utils;

import com.moonlit.generator.common.encrypt.RsaUtils;
import com.moonlit.generator.generator.entity.GenDatabase;
import com.moonlit.generator.generator.entity.vo.DatabaseTablesVO;

import java.sql.*;
import java.util.ArrayList;

/**
 * 库工具类
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:59
 * @email by.Moonlit@hotmail.com
 */
public class DbUtils {

    /**
     * 獲取表字段信息
     */
    private static final String get = "";

    /**
     * 連接數據庫
     *
     * @param genDatabase 实体
     * @param publicKey   公钥
     * @return 連接對象
     */
    private static Connection connectMySql(GenDatabase genDatabase, String publicKey) {
        Connection connection = null;

        String address = genDatabase.getDbAddress();
        String port = genDatabase.getDbPort();
        String userName = RsaUtils.publicDecrypt(genDatabase.getUserName(), publicKey);
        String password = RsaUtils.publicDecrypt(genDatabase.getPassword(), publicKey);
        String url = "jdbc:mysql://" + address + ":" + port;
        try {
            Class.forName(genDatabase.getDriverClassName());
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 關閉連接
     *
     * @param connection 連接對象
     * @param statement  語句對象
     */
    private static void close(Connection connection, Statement statement) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 獲取表詳情
     *
     * @param genDatabase 实体
     * @param publicKey   公钥
     * @return 結果集
     */
    public static ArrayList<DatabaseTablesVO> getTablesDetails(GenDatabase genDatabase, String publicKey, String tableName) {
        Connection connection = connectMySql(genDatabase, publicKey);

        String sql = "SELECT TABLE_NAME AS tableName, TABLE_COMMENT AS tableComment FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ?;";

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, tableName);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(connection, statement);
        }

        return null;
    }

}