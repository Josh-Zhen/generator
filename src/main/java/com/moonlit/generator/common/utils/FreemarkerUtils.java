package com.moonlit.generator.common.utils;

import cn.hutool.core.util.ObjectUtil;
import com.moonlit.generator.common.constant.DatabaseConstants;
import com.moonlit.generator.generator.entity.GenTableColumn;
import com.moonlit.generator.generator.entity.bo.FreemarkerConditionBO;
import com.moonlit.generator.generator.entity.bo.TableConfigAndDataAndColumnsBO;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 * freemarker工具類
 *
 * @author Joshua
 * @version 1.0
 * @date 14/7/2022 0:09
 * @email by.Moonlit@hotmail.com
 */
public class FreemarkerUtils {

    /**
     * 模板文件后缀名
     */
    public static String SUFFIX = ".ftl";

    /**
     * 加載模板
     *
     * @param templateName 模板名稱
     */
    public static Template load(String templateName) throws IOException {
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        // 加載模板路徑
        configuration.setDirectoryForTemplateLoading(new File(FilesUtils.getPath()));
        // 設置字符集
        configuration.setDefaultEncoding(OutputFormat.Defaults.Encoding);
        // 加載模板
        return configuration.getTemplate(templateName + SUFFIX);
    }

    /**
     * 構建條件
     *
     * @param dataBo 数据内容
     * @return 條件
     */
    public static FreemarkerConditionBO buildCondition(TableConfigAndDataAndColumnsBO dataBo) {
        FreemarkerConditionBO conditionBO = new FreemarkerConditionBO(dataBo);
        // 處理列信息
        handleColumns(conditionBO, dataBo.getTableColumns());

        // TODO 後續需要補上用戶自定義的類型
        return conditionBO;
    }

    /**
     * 處理列信息
     *
     * @param conditionBO 数据内容
     * @param columns     字段信息集合
     */
    private static void handleColumns(FreemarkerConditionBO conditionBO, List<GenTableColumn> columns) {
        for (GenTableColumn column : columns) {
            // 處理特殊數據類型導包
            conditionBO.setImportList(handleImportList(column));
            // 設置主鍵列
            conditionBO.setPrimaryKeyColumn(filterPrimaryKeyColumn(column));
        }
        // 如果沒有主鍵則使用第一列作爲默認主鍵列
        if (ObjectUtil.isEmpty(conditionBO.getPrimaryKeyColumn())) {
            conditionBO.setPrimaryKeyColumn(columns.get(0));
        }
    }

    /**
     * 處理特殊數據類型導包
     *
     * @param column 字段信息
     * @return 返回需要导入的包列表
     */
    private static HashSet<String> handleImportList(GenTableColumn column) {
        HashSet<String> importList = new HashSet<>();
        // 判斷數據類型
        if (DatabaseConstants.LOCAL_DATE_TIME_TYPE.equals(column.getJavaType())) {
            importList.add("java.time.LocalDateTime");
            importList.add("cn.hutool.core.date.DatePattern");
            importList.add("com.fasterxml.jackson.annotation.JsonFormat");
        } else if (DatabaseConstants.BIG_DECIMAL_TYPE.equals(column.getJavaType())) {
            importList.add("java.math.BigDecimal");
        }
        return importList;
    }

    /**
     * 篩選主鍵列
     *
     * @param column 字段信息
     * @return 主鍵列
     */
    private static GenTableColumn filterPrimaryKeyColumn(GenTableColumn column) {
        if (column.getIsPrimaryKey()) {
            return column;
        }
        return null;
    }
}
