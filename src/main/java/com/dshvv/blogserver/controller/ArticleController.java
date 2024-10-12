package com.dshvv.blogserver.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dshvv.blogserver.entity.Article;
import com.dshvv.blogserver.utils.JsonUtils;
import com.dshvv.blogserver.utils.MdHelper;
import com.dshvv.blogserver.vo.ArticleVO;
import com.dshvv.blogserver.service.IArticleService;
import com.dshvv.blogserver.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 丁少华
 * @since 2022-01-17
 */
@RequestMapping("/article")
@RestController
public class ArticleController {
    @Autowired
    private IArticleService articleService;

    @Autowired
    private MdHelper mdHelper;


    @ApiOperation("文章列表")
    @GetMapping
    private JsonResult<IPage<ArticleVO>> getList(ArticleVO articleVO, Page page) {
        IPage<ArticleVO> articlePage = articleService.getArticlePage(page, articleVO);
        return JsonResult.success(articlePage);
    }


    @ApiOperation("文章详情")
    @GetMapping("/{id}")
    private JsonResult<ArticleVO> getById(@PathVariable int id) {
        ArticleVO article = articleService.getOneWithType(id);
        return JsonResult.success(article);
    }

    @ApiOperation("新增文章")
    @PostMapping
    private JsonResult<Boolean> save(@RequestBody Article article) {
        StpUtil.checkLogin();
        article.setCreateTime(LocalDateTime.now());
        String desc = mdHelper.toText(article.getContent()).substring(0,300);
        article.setDescription(desc);
        boolean result = articleService.save(article);
        return JsonResult.success(result);
    }

    @ApiOperation("更新文章")
    @PutMapping
    private JsonResult<Boolean> updateById(@RequestBody Article article) {
        StpUtil.checkLogin();
        String desc = mdHelper.toText(article.getContent()).substring(0,300);
        article.setDescription(desc);
        boolean result = articleService.updateById(article);
        return JsonResult.success(result);
    }

    @ApiOperation("删除文章")
    @DeleteMapping("/{id}")
    private JsonResult<Boolean> removeById(@PathVariable int id) {
        StpUtil.checkLogin();
        boolean result = articleService.removeById(id);
        return JsonResult.success(result);
    }
}
