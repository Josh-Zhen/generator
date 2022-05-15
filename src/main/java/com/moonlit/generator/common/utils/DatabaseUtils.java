package com.moonlit.generator.common.utils;

import com.moonlit.generator.common.constant.CharacterConstant;
import com.moonlit.generator.common.encrypt.AesUtils;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.generator.constants.error.DatabaseErrorCode;
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
public class DatabaseUtils {

    /**
     * 連接數據庫
     *
     * @param genDatabase 实体
     * @param key         密鑰
     * @return 連接對象
     */
    private static Connection connectMySql(GenDatabase genDatabase, String key) {
        Connection connection;

        String address = genDatabase.getAddress();
        String port = CharacterConstant.COLON + genDatabase.getPort();
        String userName = AesUtils.decryptBase64(genDatabase.getUserName(), key);
        String password = AesUtils.decryptBase64(genDatabase.getPassword(), key);
        String url = "jdbc:mysql://" + address + port;
        try {
            Class.forName(genDatabase.getDriverClassName());
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new BusinessException(DatabaseErrorCode.UNABLE_TO_CONNECT);
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
            throw new BusinessException(DatabaseErrorCode.UNABLE_TO_CONNECT);
        }
    }

    /**
     * 獲取表詳情
     *
     * @param genDatabase 实体
     * @param key         密鑰
     * @return 結果集
     */
    public static ArrayList<DatabaseTablesVO> getTablesDetails(GenDatabase genDatabase, String key) {
        ArrayList<DatabaseTablesVO> listVo = new ArrayList<>();
        Connection connection = connectMySql(genDatabase, key);
        String sql = "SELECT TABLE_NAME, TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, genDatabase.getName());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                listVo.add(new DatabaseTablesVO(resultSet.getString("TABLE_NAME"), resultSet.getString("TABLE_COMMENT")));
            }
        } catch (SQLException e) {
            throw new BusinessException(DatabaseErrorCode.UNABLE_TO_CONNECT);
        } finally {
            close(connection, statement);
        }
        return listVo;
    }

}