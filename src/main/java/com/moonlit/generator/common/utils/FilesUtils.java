package com.moonlit.generator.common.utils;

import com.moonlit.generator.common.constant.CharacterConstant;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.generator.constants.error.TemplateErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * 文件操作類
 *
 * @author Joshua
 * @version 1.0
 * @date 1/6/2022 15:29
 * @email by.Moonlit@hotmail.com
 */
@Slf4j
public class FilesUtils {

    /**
     * 獲取模板路徑
     *
     * @return 返回路径
     * @throws FileNotFoundException 找不到文件異常
     */
    public static String getPath() throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:").getPath() + "\\template";
    }

    /**
     * 初始化模板文件夾
     */
    public static void initializationFolder() {
        try {
            // 模板文件夹路径
            String templatePath = getPath();
            // 模板文件夹存在时刪除
            deleteFile(templatePath);
            Path path = Paths.get(templatePath);
            // 重新創建文件夾
            Files.createDirectory(path);
        } catch (IOException e) {
            throw new BusinessException(TemplateErrorCode.CREATE_TEMPLATE_FOLDER_ERROR);
        }
    }

    /**
     * 刪除文件夾
     *
     * @param path 文件路徑
     */
    private static void deleteFile(String path) {
        File directory = new File(path);
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            file.delete();
        }
        directory.delete();
    }

    /**
     * 將模板寫入文件内
     *
     * @param content  模板内容
     * @param fileName 文件名稱
     */
    public static void createTemplateFile(String content, String fileName) {
        initializationFolder();
        try {
            fileName = getPath() + CharacterConstant.RIGHT_DIVIDE + fileName + ".ftl";
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.write(content);
            out.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
