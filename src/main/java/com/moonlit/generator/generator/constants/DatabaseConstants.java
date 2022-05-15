package com.moonlit.generator.generator.constants;

/**
 * 數據庫數據類型通用常量
 *
 * @author Joshua
 * @version 1.0
 * @date 19/12/2021 0:20
 * @email by.Moonlit@hotmail.com
 */
public class DatabaseConstants {

    /**
     * 字符型
     */
    public static final String STRING_TYPE = "String";

    /**
     * 整型
     */
    public static final String INTEGER_TYPE = "Integer";

    /**
     * 長整型
     */
    public static final String LONG_TYPE = "Long";

    /**
     * 浮點型
     */
    public static final String DOUBLE_TYPE = "Double";

    /**
     * 時間類型
     */
    public static final String LOCAL_DATE_TIME_TYPE = "LocalDateTime";

    /**
     * 高精度計數型
     */
    public static final String BIG_DECIMAL_TYPE = "BigDecimal";

    /**
     * 數據庫字符串類型
     */
    public static final String[] DATABASE_STRING_TYPE = {"char", "varchar", "tinytext", "text", "mediumtext", "longtext"};

    /**
     * 數據庫時間類型
     */
    public static final String[] DATABASE_DATE_TYPE = {"datetime", "time", "date", "timestamp"};

    /**
     * 數據庫數字類型
     */
    public static final String[] DATABASE_NUMBER_TYPE = {"tinyint", "smallint", "mediumint", "int", "number", "integer",
            "bigint", "float", "float", "double", "decimal"};

}
