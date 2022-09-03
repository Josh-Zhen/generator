package com.moonlit.generator.generator.constants;

import com.moonlit.generator.common.constant.CharacterConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 數據庫驅動常量
 *
 * @author Joshua
 * @version 1.0
 * @date 15/5/2022 16:49
 * @email by.Moonlit@hotmail.com
 */
@Getter
@AllArgsConstructor
public enum DatabaseDriverConstant {

    // 驅動
    MySQL("com.mysql.cj.jdbc.Driver"),
    PostgreSQL("org.postgresql.Driver"),
    Oracle("oracle.jdbc.OracleDriver"),
    SQLite("org.sqlite.JDBC"),
    MariaDB("org.mariadb.jdbc.Driver"),
    ;

    private String driven;

    /**
     * 根據數據庫類型獲取數據庫驅動
     *
     * @param databaseType 數據庫名稱
     * @return 數據庫驅動
     */
    public static String getDriverClass(String databaseType) {
        String driven = CharacterConstant.EMPTY;
        for (DatabaseDriverConstant databaseDriverConstant : DatabaseDriverConstant.values()) {
            if (databaseType.equals(databaseDriverConstant.name())) {
                driven = databaseDriverConstant.getDriven();
            }
        }
        return driven;
    }
}
