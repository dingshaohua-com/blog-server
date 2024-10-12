package com.dshvv.blogserver.vo;

import io.swagger.annotations.ApiModel;

/**
 * <p>
 *
 * </p>
 *
 * @author 丁少华
 * @since 2022-01-25
 */
@ApiModel(value = "ArticleCountVO对象")
public class ArticleCountVO {
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    private String typeId;
    private String typeName;
    private Long total;
}
