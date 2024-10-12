package com.dshvv.blogserver.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dshvv.blogserver.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dshvv.blogserver.vo.ArticleCountVO;
import com.dshvv.blogserver.vo.ArticleVO;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 丁少华
 * @since 2022-01-18
 */
public interface ArticleMapper extends BaseMapper<Article> {
    ArticleVO getOneWithType(int id);
    IPage<ArticleVO> getArticlePage(@Param("page")Page page, @Param("articleVO")ArticleVO articleVO);
    List<ArticleCountVO> getArticleCount();
}