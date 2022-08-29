package com.moonlit.generator.common.utils;

import com.moonlit.generator.generator.constants.DatabaseConstants;
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
        // 處理特殊數據類型導包
        conditionBO.setImportList(handleImportList(dataBo.getTableColumns()));

        return conditionBO;
    }

    /**
     * 處理特殊數據類型導包
     *
     * @param columns 字段信息
     * @return 返回需要导入的包列表
     */
    private static HashSet<String> handleImportList(List<GenTableColumn> columns) {
        HashSet<String> importList = new HashSet<>();
        for (GenTableColumn column : columns) {
            // 判斷數據類型
            if (DatabaseConstants.LOCAL_DATE_TIME_TYPE.equals(column.getJavaType())) {
                importList.add("java.time.LocalDateTime");
                importList.add("cn.hutool.core.date.DatePattern");
                importList.add("com.fasterxml.jackson.annotation.JsonFormat");
            } else if (DatabaseConstants.BIG_DECIMAL_TYPE.equals(column.getJavaType())) {
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
    }

}
