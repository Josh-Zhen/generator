package com.moonlit.generator.controller;

import com.moonlit.generator.common.page.PageResult;
import com.moonlit.generator.common.response.Result;
import com.moonlit.generator.entity.vo.DbTableDetail;
import com.moonlit.generator.service.DbDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据库明细控制层
 *
 * @author Joshua
 * @version 1.0
 * @date 2021/9/30 10:30
 * @email by.Moonlit@hotmail.com
 */
@RestController
@RequestMapping("/dbDetail")
public class DbDetailController {

    @Autowired
    private DbDetailService dbDetailService;

    /**
     * 获取库表详情
     *
     * @param dbTableDetail 查询实体
     * @return 结果集
     */
    @GetMapping("/getDbList")
    public Result<PageResult<DbTableDetail>> getDbList(DbTableDetail dbTableDetail) {
        return Result.success(dbDetailService.getDbList(dbTableDetail));
    }


}
