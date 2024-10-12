package com.dshvv.blogserver.vo;

import com.dshvv.blogserver.entity.User;

import java.util.List;

public class MasterInfoVO extends User {


    public List<ArticleCountVO> getArticleCounts() {
        return articleCounts;
    }

    public void setArticleCounts(List<ArticleCountVO> articleCounts) {
        this.articleCounts = articleCounts;
    }

    private List <ArticleCountVO> articleCounts;
}
