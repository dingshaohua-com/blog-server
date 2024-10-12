package com.dshvv.blogserver.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dshvv.blogserver.entity.Article;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 丁少华
 * @since 2022-01-17
 */
@ApiModel(value = "ArticleVO对象")
public class ArticleVO extends Article {
    private String typeName;
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
