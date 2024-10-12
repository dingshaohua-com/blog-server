package com.dshvv.blogserver.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dshvv.blogserver.entity.Elog;
import com.dshvv.blogserver.service.IArticleService;
import com.dshvv.blogserver.service.IElogService;
import com.dshvv.blogserver.utils.JsonResult;
import com.dshvv.blogserver.vo.ArticleVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 丁少华
 * @since 2022-03-22
 */
@RequestMapping("/log")
@RestController
public class ElogController {
    @Autowired
    private IElogService iElogService;

    @ApiOperation("新增日志")
    @PostMapping
    public JsonResult<Boolean> uploadLog(@RequestBody Map<String, String> map) {
        Elog elog = new Elog();
        elog.setInfo(map.get("info"));
        boolean result = iElogService.save(elog);
        return JsonResult.success(result);
    }

    @ApiOperation("日志列表")
    @GetMapping
    private JsonResult<List<Elog>> getList() {
        List<Elog> list = iElogService.list();
        return JsonResult.success(list);
    }
}
