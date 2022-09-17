package com.moonlit.generator.common.utils;

import cn.hutool.core.util.ObjectUtil;
import com.moonlit.generator.common.constant.CharacterConstant;
import com.moonlit.generator.common.constant.DatabaseConstants;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.generator.constants.error.TemplateErrorCode;
import com.moonlit.generator.generator.entity.GenTableColumn;
import com.moonlit.generator.generator.entity.GenTemplateConfig;
import com.moonlit.generator.generator.entity.bo.FreemarkerConditionBO;
import com.moonlit.generator.generator.entity.bo.TableConfigAndDataAndColumnsBO;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
     * 創建模板文件
     *
     * @param list 模板集合
     * @return 文件名稱
     */
    public static ArrayList<String> createTemplateFile(Collection<GenTemplateConfig> list) {
        // 模板不存在
        if (list.size() == 0) {
            throw new BusinessException(TemplateErrorCode.TEMPLATE_DOES_NOT_EXIST);
        }
        // 模板文件名稱
        ArrayList<String> fileNames = new ArrayList<>();
        // 初始化文件夾
        FilesUtils.initializationFolder();

        // 循環生成模板
        for (GenTemplateConfig templateConfig : list) {
            // 處理富文本數據
            String templateData = TemplateUtils.formatText(templateConfig.getTemplate());
            // 文件名稱 (格式：模板組編號-模板名稱.模板後綴名)
            String fileName = templateConfig.getCollectionId() + CharacterConstant.HYPHEN + templateConfig.getName()
                    + CharacterConstant.PERIOD + templateConfig.getSuffixName();
            // 生成模板
            FilesUtils.createTemplateFile(fileName, templateData);
            // 保存模板文件名
            fileNames.add(fileName);
        }
        return fileNames;
    }

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
     * @param map    用戶自定義的键值對
     * @return 條件
     */
    public static FreemarkerConditionBO buildCondition(TableConfigAndDataAndColumnsBO dataBo, HashMap<String, Object> map) {
        FreemarkerConditionBO conditionBO = new FreemarkerConditionBO(dataBo);
        // 處理列信息
        handleColumns(conditionBO, dataBo.getTableColumns());
        if (map.size() > 0) {
            conditionBO.setMap(map);
        }
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
