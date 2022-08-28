package com.moonlit.generator.common.utils;

import com.moonlit.generator.generator.entity.bo.FreemarkerConditionBO;
import com.moonlit.generator.generator.entity.bo.TableConfigAndDataAndColumnsBO;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;

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

//        Collection<GenTableColumn> tableColumns = dataBo.getTableColumns();
//        ArrayList<String> columnsName = new ArrayList<>();
//        for (GenTableColumn tableColumn : tableColumns) {
//            // 獲取每列名稱
//            columnsName.add(tableColumn.getColumnName());
//            // 獲取每列數據類型
//        }
//        conditionBO.setColumnsName(columnsName);

        return conditionBO;
    }

}
