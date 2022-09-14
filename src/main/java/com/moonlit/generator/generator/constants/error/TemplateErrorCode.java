package com.moonlit.generator.generator.constants.error;

import com.moonlit.generator.common.exception.base.AbstractBaseExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 模板異常類
 *
 * @author Joshua
 * @version 1.0
 * @date 14/7/2022 23:46
 * @email by.Moonlit@hotmail.com
 */
@Getter
@AllArgsConstructor
public enum TemplateErrorCode implements AbstractBaseExceptionEnum {
    /**
     * 枚举
     */
    CREATE_TEMPLATE_FOLDER_ERROR(13000, "创建模板文件夹失败"),
    TEMPLATE_DOES_NOT_EXIST(13001, "模板不存在"),
    EXPORT_CODE_EXCEPTION(13002, "导出代码异常"),
    DEFAULT_GROUP_CANNOT_OPERATE(13003, "默认组无法操作，请选择其他组"),
    UNABLE_DELETE_FOR_DEFAULT_GROUP(13004, "无法删除默认组的数据"),
    UNABLE_DELETE_FOR_DEFAULT(13005, "无法删除默认组的模板"),
    ;

    private Integer code;
    private String message;
}
