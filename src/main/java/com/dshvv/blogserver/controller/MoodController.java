package com.dshvv.blogserver.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dshvv.blogserver.entity.Article;
import com.dshvv.blogserver.entity.Mood;
import com.dshvv.blogserver.service.IMoodService;
import com.dshvv.blogserver.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 丁少华
 * @since 2022-01-17
 */
@RestController
@RequestMapping("/mood")
public class MoodController {
    @Autowired
    private IMoodService moodService;

    @ApiOperation("说说列表")
    @GetMapping
    private JsonResult<IPage<Mood>> getList(Integer current, Integer size){
        if(ObjectUtils.isEmpty(current) || ObjectUtils.isEmpty(size)){ //如果没有分页
            List<Mood> moods = moodService.list(new QueryWrapper<Mood>().orderByDesc("create_time"));
            return JsonResult.success(moods);
        }else{
            IPage<Mood> moods = moodService.page(new Page(current, size),new QueryWrapper<Mood>().orderByDesc("create_time"));
            return JsonResult.success(moods);
        }
    }

    @ApiOperation("说说详情")
    @GetMapping("/{id}")
    private JsonResult<Mood> getById(@PathVariable int id){
        Mood mood = moodService.getById(id);
        return JsonResult.success(mood);
    }

    @ApiOperation("新增说说")
    @PostMapping
    private JsonResult<Boolean> save(@RequestBody Mood mood){
        StpUtil.checkLogin();
        mood.setCreateTime(LocalDateTime.now());
        boolean result = moodService.save(mood);
        return JsonResult.success(result);
    }

    @ApiOperation("更新说说")
    @PutMapping
    private JsonResult<Boolean> updateById(@RequestBody Mood mood){
        StpUtil.checkLogin();
        boolean result = moodService.updateById(mood);
        return JsonResult.success(result);
    }

    @ApiOperation("删除说说")
    @DeleteMapping("/{id}")
    private JsonResult<Boolean> removeById(@PathVariable int id){
        StpUtil.checkLogin();
        boolean result = moodService.removeById(id);
        return JsonResult.success(result);
    }
}
