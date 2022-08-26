package com.moonlit.generator.generator.entity.bo;

import cn.hutool.core.util.ObjectUtil;
import com.moonlit.generator.generator.entity.GenTableColumn;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * 預覽和生成代碼需要的信息内容
 * 包含作者配置、數據表配置、表字段配置
 *
 * @author Joshua
 * @version 1.0
 * @date 26/8/2022 22:47
 * @email by.Moonlit@hotmail.com
 */
@Data
public class TableConfigAndDataAndColumnsBO {

    /*-- 作者配置表 --*/

    /**
     * id
     */
    private Long tableConfigId;

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
     * 日期格式
     */
    private String dateFormat;

    /**
     * 時間
     */
    private String datetime;

    /*-- 數據表配置表 --*/

    /**
     * 表描述
     */
    private String tableComment;

    /**
     * 对象名
     */
    private String objectName;

    /**
     * 業務名
     */
    private String businessName;

    /**
     * 功能名
     */
    private String functionName;

    /*-- 表字段配置表 --*/

    /**
     * 字段信息集合
     */
    ArrayList<GenTableColumn> tableColumns;

    /*--------------------------- 構造器 ---------------------------*/

    /**
     * 時間構造器
     *
     * @return 時間
     */
    public String getDatetime() {
        // 默認的時間格式
        String dateTimeFormat = "dd/MM/yyyy HH:mm";
        // 動態時間格式
        if (ObjectUtil.isNotEmpty(this.dateFormat)) {
            dateTimeFormat = dateFormat;
        }
        datetime = DateTimeFormatter.ofPattern(dateTimeFormat).format(LocalDateTime.now());
        return datetime;
    }

}
