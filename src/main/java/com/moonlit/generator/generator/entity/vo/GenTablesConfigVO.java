package com.moonlit.generator.generator.entity.vo;

import cn.hutool.core.util.ObjectUtil;
import com.moonlit.generator.common.constant.CharacterConstant;
import com.moonlit.generator.generator.entity.GenTablesConfig;
import io.swagger.annotations.ApiModelProperty;
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
public class GenTablesConfigVO {

    /**
     * id
     */
    @ApiModelProperty(name = "id", value = "id")
    private Long id;

    /**
     * 配置名稱
     */
    @ApiModelProperty(name = "name", value = "配置名稱")
    private String name;

    /**
     * 備注
     */
    @ApiModelProperty(name = "remark", value = "備注")
    private String remark;

    /**
     * 構造對象
     *
     * @param tablesConfig 配置實體
     */
    public GenTablesConfigVO(GenTablesConfig tablesConfig) {
        this.id = tablesConfig.getId();
        this.name = tablesConfig.getName();
        String author = "作者：" + tablesConfig.getAuthor();
        String packageName = " | 包名：" + tablesConfig.getPackageName();
        String moduleName = ObjectUtil.isNotNull(tablesConfig.getModuleName()) ? " | 模块名：" + tablesConfig.getModuleName() : CharacterConstant.EMPTY;
        String tablePrefix = ObjectUtil.isNotNull(tablesConfig.getTablePrefix()) ? " | 表前綴：" + tablesConfig.getTablePrefix() : CharacterConstant.EMPTY;
        String removePrefix = tablesConfig.getRemovePrefix() ? "是" : "否";
        this.remark = author + packageName + moduleName + tablePrefix + " | 是否移除表前綴：" + removePrefix;
    }
}
