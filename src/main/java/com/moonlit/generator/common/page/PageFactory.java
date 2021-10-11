package com.moonlit.generator.common.page;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moonlit.generator.utils.db.HttpServletUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 默认分页参数构建
 *
 * @author tangjx
 */
public class PageFactory {

    /**
     * 每页大小（默认20）
     */
    private static final String PAGE_SIZE_PARAM_NAME = "pageSize";

    /**
     * 第几页（从1开始）
     */
    private static final String PAGE_NO_PARAM_NAME = "pageNo";

    /**
     * 默认分页，在使用时PageFactory.defaultPage会自动获取pageSize和pageNo参数
     */
    public static <T> Page<T> defaultPage() {

        int pageSize = 20;
        int pageNo = 1;

        HttpServletRequest request = HttpServletUtil.getRequest();

        //每页条数
        String pageSizeString = request.getParameter(PAGE_SIZE_PARAM_NAME);
        if (ObjectUtil.isNotEmpty(pageSizeString)) {
            pageSize = Integer.parseInt(pageSizeString);
            // 每页大于50条数据时，使用默认值
            if (50 < Integer.parseInt(pageSizeString)) {
                pageSize = 20;
            }
        }

        //第几页
        String pageNoString = request.getParameter(PAGE_NO_PARAM_NAME);
        if (ObjectUtil.isNotEmpty(pageNoString)) {
            pageNo = Integer.parseInt(pageNoString);
        }

        return new Page<>(pageNo, pageSize);
    }

}
