package com.moonlit.generator.common.page;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moonlit.generator.common.utils.HttpServletUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 默认分页参数构建
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/10/14 8:39
 * @email by.Moonlit@hotmail.com
 */
public class PageFactory {

    /**
     * 每页大小（默认10）
     */
    private static final String DEFAULT_PAGE_SIZE = "pageSize";

    /**
     * 第几页（从1开始）
     */
    private static final String DEFAULT_PAGE_NO = "pageNo";

    /**
     * 默认分页，在使用时PageFactory.defaultPage会自动获取pageSize和pageNo参数
     */
    public static <T> Page<T> defaultPage() {
        HttpServletRequest request = HttpServletUtils.getRequest();
        int pageSize = 20;
        int pageNo = 1;

        //每页条数
        String pageSizeString = request.getParameter(DEFAULT_PAGE_SIZE);
        if (ObjectUtil.isNotEmpty(pageSizeString)) {
            int parseInt = Integer.parseInt(pageSizeString);
            // 每页大于50条数据时，使用默认值
            pageSize = parseInt > 50 ? pageSize : parseInt;
        }

        //第几页
        String pageNoString = request.getParameter(DEFAULT_PAGE_NO);
        if (ObjectUtil.isNotEmpty(pageNoString)) {
            pageNo = Integer.parseInt(pageNoString);
        }

        return new Page<>(pageNo, pageSize);
    }

}
