package com.moonlit.generator.common.utils;

import cn.hutool.core.io.file.PathUtil;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件操作類
 *
 * @author Joshua
 * @version 1.0
 * @date 1/6/2022 15:29
 * @email by.Moonlit@hotmail.com
 */
public class FilesUtils {

    /**
     * 判斷模板文件夾是否存在，不存在則創建
     *
     * @return 返回路径
     */
    public static String generateTemplateFolder() {
        String templatePath = "";
        try {
            // 模板文件夹路径
            templatePath = ResourceUtils.getFile("classpath:").getPath() + "\\template";
            Path path = Paths.get(templatePath);
            // 模板文件夹不存在时创建
            if (!PathUtil.isDirectory(path)) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return templatePath;
    }


}
