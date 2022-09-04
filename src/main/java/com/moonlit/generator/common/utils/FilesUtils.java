package com.moonlit.generator.common.utils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import com.moonlit.generator.common.constant.CharacterConstant;
import com.moonlit.generator.common.exception.BusinessException;
import com.moonlit.generator.generator.constants.error.TemplateErrorCode;
import com.moonlit.generator.generator.entity.bo.FreemarkerConditionBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
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
     * 默認路徑
     */
    private static final String DEFAULT_PATH = "default";
    /**
     * java文件路徑
     */
    private static final String JAVA_PATH = "main/java";
    /**
     * 前端文件路徑
     */
    private static final String WEB_PATH = "web";

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
            File directory = new File(templatePath);
            if (directory.exists() && directory.isDirectory()) {
                deleteFile(directory);
            }
            // 重新創建文件夾
            Files.createDirectory(Paths.get(templatePath));
        } catch (IOException e) {
            throw new BusinessException(TemplateErrorCode.CREATE_TEMPLATE_FOLDER_ERROR);
        }
    }

    /**
     * 刪除文件夾
     *
     * @param directory 文件夾路徑
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void deleteFile(File directory) {
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            file.delete();
        }
        directory.delete();
    }

    /**
     * 將模板寫入文件内
     *
     * @param fileName 文件名稱
     * @param content  模板内容
     */
    public static void createTemplateFile(String fileName, String content) {
        try {
            fileName = getPath() + CharacterConstant.RIGHT_DIVIDE + fileName + FreemarkerUtils.SUFFIX;
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            out.write(content);
            out.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 構建zip文件
     *
     * @param fileName 壓縮包名稱
     * @param response http響應頭
     * @param data     數據
     */
    public static void constructZipFile(String fileName, HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".zip");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IoUtil.write(response.getOutputStream(), true, data);
    }

    /**
     * 獲取模板文件名
     *
     * @param condition    模板數據
     * @param templateName 模板名稱(帶後綴)
     * @return 文件名
     */
    public static String getFileName(FreemarkerConditionBO condition, String templateName) {
        String fileName = "";
        // 如果模塊名不存在則不添加
        String moduleNameAndBusinessName = (ObjectUtil.isEmpty(condition.getModuleName()) ? CharacterConstant.EMPTY : CharacterConstant.LEFT_DIVIDE + condition.getModuleName())
                + CharacterConstant.LEFT_DIVIDE + condition.getBusinessName();
        // java的路徑( main/java/包名/模塊名（存在）/業務名 )
        String javaPath = JAVA_PATH + CharacterConstant.LEFT_DIVIDE +
                // 將“.”轉為“/”
                condition.getPackageName().replace(CharacterConstant.PERIOD, CharacterConstant.LEFT_DIVIDE) + moduleNameAndBusinessName;

        // 判斷是否是java類型文件(去掉後綴名，並轉成小寫)
        String templateFileName = templateName.split(CharacterConstant.ESCAPE_COMMA)[0].toLowerCase();
        String suffix = templateName.split(CharacterConstant.ESCAPE_COMMA)[1].toLowerCase();
        if ("java".equals(suffix) || "xml".equals(suffix)) {
            fileName += javaPath;
            if ("serviceimpl".equals(templateFileName)) {
                fileName += "/service/impl";
            } else if ("mapping".equals(templateFileName)) {
                fileName += "/mapper/mapping";
            } else {
                fileName = templateFileName;
            }
            fileName = fileName + CharacterConstant.LEFT_DIVIDE + templateName;
        } else if ("vue".equals(suffix) || "js".equals(suffix)) {
            fileName += WEB_PATH;
            // 判斷是否是web頁面類型
            switch (templateFileName) {
                case "list":
                    fileName = "/views/" + moduleNameAndBusinessName + CharacterConstant.LEFT_DIVIDE + templateName;
                    break;
                case "modal":
                    fileName = "/views/" + moduleNameAndBusinessName + "/modules/" + templateName;
                    break;
                case "api":
                    fileName = fileName + "/api/" + moduleNameAndBusinessName + condition.getObjectName() + suffix;
                    break;
                default:
            }
        } else {
            // 其他文件
            fileName = DEFAULT_PATH + CharacterConstant.LEFT_DIVIDE + templateName;
        }
        System.out.println("-----文件路徑為：" + fileName);
        return fileName;
    }
}
