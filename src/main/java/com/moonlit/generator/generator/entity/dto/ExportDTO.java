package com.moonlit.generator.generator.entity.dto;

import lombok.Data;

import java.util.ArrayList;

/**
 * 導出文件DTO實體
 *
 * @author Joshua
 * @version 1.0
 * @date 12/9/2022 14:27
 * @email by.Moonlit@hotmail.com
 */
@Data
public class ExportDTO {

    /**
     * 文件名稱
     */
    private String fileName;

    /**
     * 表id
     */
    private ArrayList<Long> tableIds;

    /**
     * 表配置id
     */
    private Long tableConfigId;

    /**
     * 模板組id
     */
    private Long collectionId;
}
