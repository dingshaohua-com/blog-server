package com.dshvv.blogserver.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dshvv.blogserver.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dshvv.blogserver.vo.ArticleCountVO;
import com.dshvv.blogserver.vo.ArticleVO;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 丁少华
 * @since 2022-01-18
 */
public interface IArticleService extends IService<Article> {
    ArticleVO getOneWithType(int id);
    IPage<ArticleVO> getArticlePage(Page page, ArticleVO articleVO);
    List<ArticleCountVO> getArticleCount();
}
