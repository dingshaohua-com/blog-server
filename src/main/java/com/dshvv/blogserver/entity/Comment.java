package com.dshvv.blogserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author 丁少华
 * @since 2022-06-08
 */
@ApiModel(value = "Comment对象", description = "")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String content;

    private LocalDateTime createTime;

    private Integer replyArticleId;

    @ApiModelProperty("评论人头像")
    private String avatar;

    @ApiModelProperty("评论人邮箱")
    private String email;

    @ApiModelProperty("评论人博客地址")
    private String blogUrl;

    @ApiModelProperty("回复评论的id")
    private Integer replyCmId;

    @ApiModelProperty("评论人昵称")
    private String nickName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public Integer getReplyArticleId() {
        return replyArticleId;
    }

    public void setReplyArticleId(Integer replyArticleId) {
        this.replyArticleId = replyArticleId;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getBlogUrl() {
        return blogUrl;
    }

    public void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl;
    }
    public Integer getReplyCmId() {
        return replyCmId;
    }

    public void setReplyCmId(Integer replyCmId) {
        this.replyCmId = replyCmId;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Comment{" +
            "id=" + id +
            ", content=" + content +
            ", createTime=" + createTime +
            ", replyArticleId=" + replyArticleId +
            ", avatar=" + avatar +
            ", email=" + email +
            ", blogUrl=" + blogUrl +
            ", replyCmId=" + replyCmId +
            ", nickName=" + nickName +
        "}";
    }
}
