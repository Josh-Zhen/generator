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
     * 日期+时间（不含时区信息）
     */
    public static final String LOCAL_DATE_TIME_TYPE = "LocalDateTime";

    /**
     * 時間類型
     * 日期（不含时区信息）
     */
    public static final String LOCAL_DATE_TYPE = "LocalDate";

    /**
     * 高精度計數型
     */
    public static final String BIG_DECIMAL_TYPE = "BigDecimal";

    /**
     * 數據庫字符串類型
     */
    public static final String[] DATABASE_STRING_TYPE = {"char", "varchar", "tinytext", "text", "mediumtext", "longtext"};

    /**
     * 文本類型
     */
    public static final String[] TEXT_TYPE = {"tinytext", "text", "mediumtext", "longtext"};

    /**
     * 數據庫時間類型
     */
    public static final String[] DATABASE_DATE_TYPE = {"datetime", "time", "date", "timestamp"};

    /**
     * 數據庫數字類型
     */
    public static final String[] DATABASE_NUMBER_TYPE = {"number", "tinyint", "smallint", "mediumint", "int", "integer",
            "bigint", "float", "double", "decimal"};

    /**
     * 浮點類型
     */
    public static final String[] FLOATING_POINT = {"float", "double", "decimal"};

    /**
     * 查詢條件
     * 等于-eq、不等于-neq、大于-gt、小于-lt、范围-like
     */
    public static final String[] CONDITION_TYPE = {"eq", "neq", "gt", "lt", "like"};

    /**
     * 不查詢的字段
     */
    public static final String[] COLUMN_NOT_QUERY = {"id", "create_by", "update_by", "create_time", "update_time", "remark"};

    /**
     * 查詢的字段類型
     */
    public static final String[] COLUMN_TYPE_NOT_QUERY = {"tinytext", "text", "mediumtext", "longtext"};
}
