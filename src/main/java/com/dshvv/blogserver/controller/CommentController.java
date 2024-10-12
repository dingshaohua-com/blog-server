package com.dshvv.blogserver.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dshvv.blogserver.entity.Comment;
import com.dshvv.blogserver.service.ICommentService;
import com.dshvv.blogserver.utils.EmailHelper;
import com.dshvv.blogserver.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 丁少华
 * @since 2022-01-27
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private EmailHelper emailHelper;

    @ApiOperation("评论列表")
    @GetMapping
    private JsonResult<IPage<Comment>> getList( Integer current, Integer size, Integer replyArticleId) {
        IPage<Comment> comments = commentService.page(new Page(current, size), new QueryWrapper<Comment>().eq("reply_article_id", replyArticleId).isNull("reply_cm_id").orderByDesc("create_time"));
        List finalRes = new ArrayList();
        for (int i=0; i<comments.getRecords().size(); i++){
            Comment comment = comments.getRecords().get(i);
            int id = comment.getId();
            List<Comment> list = commentService.list(new QueryWrapper<Comment>().eq("reply_cm_id", id).orderByAsc("create_time"));
            Map map = JSON.parseObject(JSON.toJSONString(comment), new TypeReference<Map<String, String>>() {});
            map.put("children", list);
            finalRes.add(map);
        }
        comments.setRecords(finalRes);

        return JsonResult.success(comments);
    }

    @ApiOperation("新增评论")
    @PostMapping
    private JsonResult<String> save(@RequestBody Comment comment)throws Exception{
        comment.setCreateTime(LocalDateTime.now());
        boolean result = commentService.save(comment);
        if(result && !comment.getReplyArticleId().equals(null)){
            String email = comment.getEmail();
            String content = comment.getContent();
            try {
                emailHelper.sendHtmlEmail(email, content ,"comment-notify");
            }catch (Exception e){
                return JsonResult.success("发送邮件错误："+e.getMessage());
            }

        }
        return JsonResult.success(result);
    }

    @ApiOperation("删除评论")
    @DeleteMapping("/{id}")
    private JsonResult<Boolean> removeById(@PathVariable int id) {
        boolean result = commentService.removeById(id);
        return JsonResult.success(result);
    }

    @ApiOperation("更新评论")
    @PutMapping
    private JsonResult<Boolean> updateById(@RequestBody Comment comment) {
        boolean result = commentService.updateById(comment);
        return JsonResult.success(result);
    }

}
