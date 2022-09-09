package com.moonlit.generator.generator.entity.vo;

import cn.hutool.core.util.ObjectUtil;
import com.moonlit.generator.common.constant.CharacterConstant;
import com.moonlit.generator.generator.entity.GenTableConfig;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 拼裝表配置數據返回給前端暫時
 *
 * @author Joshua
 * @version 1.0
 * @date 2022/2/23 17:39
 * @email by.Moonlit@hotmail.com
 */
@Data
@NoArgsConstructor
public class GenTableConfigVO {

    /**
     * id
     */
    private Long id;

    /**
     * 配置名稱
     */
    private String name;

    /**
     * 備注
     */
    private String remark;

    /**
     * 構造對象
     *
     * @param tableConfig 配置實體
     */
    public GenTableConfigVO(GenTableConfig tableConfig) {
        this.id = tableConfig.getId();
        this.name = tableConfig.getName();
        String author = "作者：" + tableConfig.getAuthor();
        String packageName = " | 包名：" + tableConfig.getPackageName();
        String moduleName = ObjectUtil.isNotEmpty(tableConfig.getModuleName()) ? " | 模块名：\"" + tableConfig.getModuleName() + "\"" : CharacterConstant.EMPTY;
        String tablePrefix = ObjectUtil.isNotEmpty(tableConfig.getTablePrefix()) ? " | 表前綴：\"" + tableConfig.getTablePrefix() + "\"" : CharacterConstant.EMPTY;
        this.remark = author + packageName + moduleName + tablePrefix + " | 是否移除表前綴：" + (tableConfig.getRemovePrefix() ? "是" : "否");
    }
}
