package com.moonlit.generator.common.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.moonlit.generator.common.constant.CharacterConstant;
import com.moonlit.generator.common.encrypt.AesUtils;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.generator.constants.error.DatabaseErrorCode;
import com.moonlit.generator.generator.entity.GenDatabase;
import com.moonlit.generator.generator.entity.vo.DatabaseTablesVO;
import com.moonlit.generator.generator.entity.vo.TableFieldVO;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;

/**
 * mySql库工具类
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 9:59
 * @email by.Moonlit@hotmail.com
 */
@Slf4j
public class MySqlUtils {

    /**
     * 連接數據庫語句
     * table_schema 數據庫名
     */
    public static final String CONNECT_DATABASE = "SELECT table_name, table_comment FROM information_schema.tables " +
            "WHERE table_schema = ?";

    /**
     * 查詢數據庫庫字段語句
     * table_schema 數據庫名
     * table_name   表名
     */
    private static final String SELECT_FIELDS = "SELECT column_name, column_key = 'PRI' AS column_key, is_nullable = 'YES' AS is_nullable, " +
            "ordinal_position, column_comment, extra = 'auto_increment' AS extra, column_type " +
            "FROM information_schema.columns WHERE table_schema = ? AND table_name = ? ORDER BY ordinal_position";

    /**
     * 連接數據庫
     *
     * @param database 实体
     * @param key      密鑰
     * @return 連接對象
     */
    private static Connection connectMySql(GenDatabase database, String key) {
        Connection connection = null;
        HikariDataSource source = new HikariDataSource();
        source.setJdbcUrl("jdbc:mysql://" + database.getAddress() + CharacterConstant.COLON + database.getPort());
        source.setUsername(AesUtils.decryptBase64(database.getUserName(), key));
        source.setPassword(AesUtils.decryptBase64(database.getPassword(), key));
        source.setDriverClassName(database.getDriverClassName());
        try {
            connection = source.getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage());
            close(connection, null);
            throw new BusinessException(DatabaseErrorCode.UNABLE_CONNECT_DATABASE);
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
            throw new BusinessException(e);
        }
    }

    /**
     * 執行數據庫語句
     *
     * @param database  連接數據庫實體
     * @param key       密鑰
     * @param sql       sql語句（）
     * @param condition 條件
     * @param t         返回類型
     * @return 結果集
     */
    public static <T> ArrayList<T> executeSql(GenDatabase database, String key, String sql, ArrayList<String> condition, Class<T> t) {
        ArrayList<T> listVo = new ArrayList<>();
        Connection connection = connectMySql(database, key);
        PreparedStatement statement = null;
        try {
            // 預裝載sql語句
            statement = connection.prepareStatement(sql);
            // 填充條件
            for (int i = 1; i <= condition.size(); i++) {
                statement.setString(i, condition.get(i - 1));
            }
            // 執行查詢
            ResultSet resultSet = statement.executeQuery();

            // 填充結果
            while (resultSet.next()) {
                // 往汎型對象填充數據
                JSONObject jsonObject = JSONUtil.createObj();
                ResultSetMetaData metaData = resultSet.getMetaData();

                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String keys = StringUtils.underlineToCamel(metaData.getColumnName(i));
                    jsonObject.set(keys, resultSet.getObject(i));
                }
                listVo.add(jsonObject.toBean(t));
            }
        } catch (SQLException e) {
            throw new BusinessException(e);
        } finally {
            close(connection, statement);
        }
        return listVo;
    }


    /**
     * 獲取表詳情
     *
     * @param database 实体
     * @param key      密鑰
     * @return 結果集
     */
    public static ArrayList<DatabaseTablesVO> getTablesDetails(GenDatabase database, String key) {
        ArrayList<String> list = new ArrayList<>();
        list.add(database.getName());
        return executeSql(database, key, CONNECT_DATABASE, list, DatabaseTablesVO.class);
    }

    /**
     * 獲取字段詳情
     *
     * @param database  实体
     * @param key       密鑰
     * @param tableName 表名
     * @return 結果集
     */
    public static ArrayList<TableFieldVO> getFieldDetails(GenDatabase database, String key, String tableName) {
        ArrayList<String> list = new ArrayList<>(2);
        list.add(database.getName());
        list.add(tableName);
        return executeSql(database, key, SELECT_FIELDS, list, TableFieldVO.class);
    }
}