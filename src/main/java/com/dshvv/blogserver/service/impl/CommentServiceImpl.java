package com.dshvv.blogserver.service.impl;

import com.dshvv.blogserver.entity.Comment;
import com.dshvv.blogserver.mapper.CommentMapper;
import com.dshvv.blogserver.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 丁少华
 * @since 2022-06-08
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
