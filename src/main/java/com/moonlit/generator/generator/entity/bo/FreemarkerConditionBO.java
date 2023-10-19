package com.moonlit.generator.generator.entity.bo;

import cn.hutool.core.util.RandomUtil;
import com.moonlit.generator.common.utils.NamingStrategyUtils;
import com.moonlit.generator.generator.entity.GenTableColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 模板數據
 *
 * @author Joshua
 * @version 1.0
 * @date 28/8/2022 0:12
 * @email by.Moonlit@hotmail.com
 */
@Data
@NoArgsConstructor
public class FreemarkerConditionBO {

    /**
     * 作者
     */
    private String author;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 時間
     */
    private String datetime;

    /**
     * 描述
     */
    private String comment;

    /**
     * 版本Uid
     */
    private String serialVersionUid;

    /**
     * 業務名
     */
    private String businessName;

    /**
     * 類名
     */
    private String className;

    /**
     * 对象名
     */
    private String objectName;

    /**
     * 功能名
     */
    private String functionName;

    /**
     * 字段信息集合
     */
    List<GenTableColumn> tableColumns;

    /**
     * 字段數據類型集合
     */
    private HashSet<String> importList;

    /**
     * 主鍵列信息
     */
    private GenTableColumn primaryKeyColumn;

    /**
     * 附加數據(數據來源於键值映射表)
     */
    private HashMap<String, Object> map;

    /**
     * 構造器
     *
     * @param dataBo 原始數據
     */
    public FreemarkerConditionBO(TableConfigAndDataAndColumnsBO dataBo) {
        this.author = dataBo.getAuthor();
        this.packageName = dataBo.getPackageName();
        this.moduleName = dataBo.getModuleName();
        this.datetime = dataBo.getDatetime();
        this.comment = dataBo.getFunctionName();
        this.serialVersionUid = RandomUtil.randomNumbers(17);
        this.businessName = dataBo.getBusinessName();
        this.className = NamingStrategyUtils.firstToUpperCase(dataBo.getObjectName());
        this.objectName = dataBo.getObjectName();
        this.functionName = dataBo.getBusinessName();
        this.tableColumns = dataBo.getTableColumns();
    }

}
