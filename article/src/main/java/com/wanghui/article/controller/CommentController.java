package com.wanghui.article.controller;

import com.wanghui.common.pojo.Comment;
import com.wanghui.article.service.CommentService;
import com.wanghui.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("comment/aid/{id}")         /*根据文章id获取评论*/
    public R getCommentByArticleId(@PathVariable String id){
        List<Comment> byArticleId = commentService.findByArticleId(id);
        return R.ok().put("comment",byArticleId);
    }

    @GetMapping("comment/id/{id}")      //根据评论id获取评论
    public R getCommentById(@PathVariable String id){
        Comment byCommentId = commentService.findByCommentId(id);
        return R.ok().put("comment",byCommentId);
    }

    @PostMapping ("comment")       /*保存评论*/
    public R saveComment(@RequestBody Comment comment){
        System.out.println(comment);
        boolean ok = commentService.saveComment(comment);
        return ok ? R.ok() : R.error();
    }

    @DeleteMapping("comment/id/{id}")    /*删除评论*/
    public R deleteComment(@PathVariable String id){
        boolean ok = commentService.deleteCommentById(id);
        return ok ? R.ok() : R.error();
    }

    @PutMapping("comment/like/{id}")
    public R likeComment(@PathVariable String id){    /*评论点赞*/
        boolean ok = commentService.likeComment(id);
        return ok ? R.ok() : R.error();
    }

    @PutMapping("comment/dislike/{id}")
    public R dislikeComment(@PathVariable String id){    /*评论取消点赞*/
        boolean ok = commentService.dislikeComment(id);
        return ok ? R.ok() : R.error();
    }

}
