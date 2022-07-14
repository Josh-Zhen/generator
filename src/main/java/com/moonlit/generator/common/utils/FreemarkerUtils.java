package com.moonlit.generator.common.utils;

import com.moonlit.generator.generator.entity.GenTable;
import com.moonlit.generator.generator.entity.GenTableConfig;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
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
        return configuration.getTemplate(templateName);
    }

    /**
     * 構建條件
     *
     * @param table       表配置
     * @param tableConfig 作者配置
     * @return 條件
     */
    public static HashMap<String, String> fillingCondition(GenTable table, GenTableConfig tableConfig) {
        HashMap<String, String> condition = new HashMap<>(10);
        condition.put("", "");

        return condition;
    }

}
