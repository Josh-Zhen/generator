package com.moonlit.generator.common.utils;

import com.moonlit.generator.generator.entity.GenTable;
import com.moonlit.generator.generator.entity.GenTableConfig;
import com.moonlit.generator.generator.entity.bo.TableConfigAndDataAndColumnsBO;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * freemarker工具類
 *
 * @author Joshua
 * @version 1.0
 * @date 14/7/2022 0:09
 * @email by.Moonlit@hotmail.com
 */
public class FreemarkerUtils {

    // 模板文件后缀名
    public static String SUFFIX = ".ftl";

    /**
     * 加載模板
     *
     * @param templateName 模板名稱
     */
    public static Template load(String templateName) throws IOException {
        Configuration configuration = new Configuration(Configuration.getVersion());
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
     * @param table       表配置
     * @param tableConfig 作者配置
     * @return 條件
     */
    public static HashMap<String, String> buildCondition(GenTable table, GenTableConfig tableConfig) {
        HashMap<String, String> condition = new HashMap<>(10);

        // TODO 表動態控制Key、後續處理模塊名跟業務名需要交換
        condition.put("packageName", tableConfig.getPackageName());
        condition.put("moduleName", tableConfig.getModuleName());
        // java模板配置
        condition.put("author", tableConfig.getAuthor());
        // TODO 通過實體類構造器生成
        // 時間格式動態獲取
        condition.put("datetime", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(LocalDateTime.now()));

        condition.put("businessName", table.getObjectName());
        condition.put("className", NamingStrategy.firstToUpperCase(table.getObjectName()));
        condition.put("objectName", table.getObjectName());
        condition.put("tableComment", table.getBusinessName());


        return condition;
    }

    /**
     * 構建條件
     *
     * @param dataBo 数据内容
     * @return 條件
     */
    public static HashMap<String, String> buildCondition(TableConfigAndDataAndColumnsBO dataBo) {
        HashMap<String, String> condition = new HashMap<>(10);

        // java模板配置
        condition.put("author", dataBo.getAuthor());
        condition.put("datetime", dataBo.getDatetime());
        // TODO 表動態控制Key、後續處理模塊名跟業務名需要交換
        condition.put("packageName", dataBo.getPackageName());
        condition.put("moduleName", dataBo.getModuleName());

        condition.put("businessName", dataBo.getObjectName());
        condition.put("className", NamingStrategy.firstToUpperCase(dataBo.getObjectName()));
        condition.put("objectName", dataBo.getObjectName());
        condition.put("tableComment", dataBo.getBusinessName());


        return condition;
    }

}
