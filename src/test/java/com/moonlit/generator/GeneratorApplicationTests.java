package com.moonlit.generator;

import com.moonlit.generator.generator.entity.GenTemplateConfig;
import com.moonlit.generator.generator.service.GenTemplateConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeneratorApplicationTests {

    @Autowired
    GenTemplateConfigService templateConfigService;

    @Test
    void contextLoads() {
        GenTemplateConfig templateConfig = templateConfigService.getById(15L);
//        System.out.println(templateConfig.getTemplate());

        System.out.println("------------------------------------------");
        System.out.println(formatText(templateConfig.getTemplate()));
        System.out.println("------------------------------------------");
    }

    public static String formatText(String template) {
        // TODO 做成字典表，方便維護
        String head = "<pre class=\"ql-syntax\" spellcheck=\"false\">";
        String end = "</pre>";
        String space = "&nbsp;";

        String lt = "&lt;";
        String gt = "&gt;";

        // hljs標簽
        String hljsClass = "<span class=\"hljs-class\">";
        String keyword = "<span class=\"hljs-keyword\">";
        String docTag = "<span class=\"hljs-doctag\">";
        String comment = "<span class=\"hljs-comment\">";
        String meta = "<span class=\"hljs-meta\">";
        String function = "<span class=\"hljs-function\">";
        String title = "<span class=\"hljs-title\">";
        String string = "<span class=\"hljs-string\">";
        String builtIn = "<span class=\"hljs-built_in\">";
        String params = "<span class=\"hljs-params\">";
        String variable = "<span class=\"hljs-variable\">";
        String name = "<span class=\"hljs-name\">";
        String tag = "<span class=\"hljs-tag\">";
        String attr = "<span class=\"hljs-attr\">";
        String symbol = "<span class=\"hljs-symbol\">";
        String number = "<span class=\"hljs-number\">";
        String type = "<span class=\"hljs-type\">";

        String span = "</span>";

        // 移除開頭與結尾
        template = template.replace(head, "");
        template = template.replace(end, "");
        // 處理標簽
        template = template.replace(hljsClass, "");
        template = template.replace(keyword, "");
        template = template.replace(docTag, "");
        template = template.replace(comment, "");
        template = template.replace(meta, "");
        template = template.replace(function, "");
        template = template.replace(title, "");
        template = template.replace(string, "");
        template = template.replace(builtIn, "");
        template = template.replace(params, "");
        template = template.replace(variable, "");
        template = template.replace(name, "");
        template = template.replace(tag, "");
        template = template.replace(attr, "");
        template = template.replace(symbol, "");
        template = template.replace(number, "");
        template = template.replace(type, "");
        template = template.replace(span, "");
        // 處理左右標簽
        template = template.replace(lt, "<");
        template = template.replace(gt, ">");
        // 處理空格
        template = template.replace(space, " ");
        return template;
    }

}
