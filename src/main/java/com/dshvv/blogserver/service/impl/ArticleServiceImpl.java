package com.dshvv.blogserver.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dshvv.blogserver.entity.Article;
import com.dshvv.blogserver.mapper.ArticleMapper;
import com.dshvv.blogserver.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dshvv.blogserver.vo.ArticleCountVO;
import com.dshvv.blogserver.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 丁少华
 * @since 2022-01-18
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ArticleVO getOneWithType(int id) {
        System.out.println(articleMapper.toString());
        return articleMapper.getOneWithType(id);
    }

    public IPage<ArticleVO> getArticlePage(Page page, ArticleVO articleVO) {
        return articleMapper.getArticlePage(page, articleVO);
    }

    @Override
    public List<ArticleCountVO> getArticleCount() {
        return articleMapper.getArticleCount();
    }


}
