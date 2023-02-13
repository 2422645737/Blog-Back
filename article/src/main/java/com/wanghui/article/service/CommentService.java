package com.wanghui.article.service;

import com.wanghui.common.pojo.Comment;

import java.util.List;

public interface CommentService {

    public List<Comment> findByArticleId(String id);        //根据文章id查询评论

    public boolean saveComment(Comment comment);         //保存评论

    public Comment findByCommentId(String id);     //根据评论id查询评论

    public boolean deleteCommentById(String id);        //根据评论id删除

    public boolean likeComment(String id);       //点赞评论

    public boolean dislikeComment(String id);
}
