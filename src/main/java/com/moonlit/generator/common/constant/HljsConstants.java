package com.moonlit.generator.common.constant;

/**
 * 富文本標簽常量
 *
 * @author Joshua
 * @version 1.0
 * @date 11/7/2022 15:08
 * @email by.Moonlit@hotmail.com
 */
public class HljsConstants {

    /**
     * 默認頭尾標簽
     */
    public static final String[] HEAD_AND_END = {"<pre class=\"ql-syntax\" spellcheck=\"false\">", "</pre>"};

    // 需要替換其他字符的標簽
    /**
     * "<"
     */
    public static final String LT = "&lt;";
    /**
     * ">"
     */
    public static final String GT = "&gt;";
    /**
     * 空格符號
     */
    public static final String SPACE = "&nbsp;";

    /**
     * 文本標簽默認替換空
     */
    public static final String[] LABEL = {"<span class=\"hljs-class\">", "<span class=\"hljs-keyword\">",
            "<span class=\"hljs-doctag\">", "<span class=\"hljs-comment\">", "<span class=\"hljs-meta\">",
            "<span class=\"hljs-number\">", "<span class=\"hljs-type\">", "<span class=\"hljs-variable\">",
            "<span class=\"hljs-function\">", "<span class=\"hljs-title\">", "<span class=\"hljs-string\">",
            "<span class=\"hljs-built_in\">", "<span class=\"hljs-params\">", "<span class=\"hljs-symbol\">",
            "<span class=\"hljs-name\">", "<span class=\"hljs-attr\">", "<span class=\"hljs-tag\">",
            "<span class=\"hljs-literal\">", "<span class=\"hljs-regexp\">", "<span class=\"hljs-subst\">",
            "<span class=\"hljs-title class_\">", "</span>"
    };
}
