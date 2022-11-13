package com.wanghui.article.service.Impl;

import com.mongodb.client.result.DeleteResult;
import com.wanghui.article.pojo.Comment;
import com.wanghui.article.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Comment> findAll() {      /*获取所有评论*/
        List<Comment> all = mongoTemplate.findAll(Comment.class);
        process(all);      //合并评论，删除二级评论
        return all;
    }

    @Override
    public List<Comment> findByArticleId(Integer id) {    /*根据文章id查找评论*/
        Query query = new Query(Criteria.where("article_id").is(id));
        List<Comment> comments = mongoTemplate.find(query, Comment.class);
        process(comments);
        return comments;
    }

    @Override
    public boolean saveComment(Comment comment) {    /*保存评论*/
        // 插入评论
        Comment insert = mongoTemplate.insert(comment);
        return insert.getId() != null;
    }

    @Override
    public Comment findByCommentId(String id) {      /*根据评论Id获取评论*/
        //只针对于一级评论的获取，不可获取二级评论
        Query query = new Query(Criteria.where("_id").is(id));
        Comment comment = mongoTemplate.findOne(query, Comment.class,"com");    //获取需要的评论
        if(comment == null || !comment.getParent_id().equals(""))return null;    //只针对一级评论
        //整合该评论下的所有评论comment_list
        else{
            List<Comment> byArticleId = findByArticleId(comment.getArticle_id());     //根据文章id获取所有的评论
            for (Comment comment1 : byArticleId) {
                if(Objects.equals(comment.getId(), comment1.getId()))return comment1;
            }
            return comment;
        }
    }

    @Override
    public boolean deleteCommentById(String id) {        //根据评论id删除评论哦
        Comment comment = findByCommentId(id);
        if(comment == null)return true;
        else{
            Query query = new Query(Criteria.where("_id").is(id));
            DeleteResult remove = mongoTemplate.remove(query, Comment.class);
            return remove.wasAcknowledged();
        }
    }

    @Override
    public boolean likeComment(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Comment one = mongoTemplate.findOne(query, Comment.class);
        if(one != null){
            one.setLike_num(one.getLike_num() + 1);
            Comment save = mongoTemplate.save(one);
            return true;
        }
        return false;
    }

    @Override
    public boolean dislikeComment(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Comment one = mongoTemplate.findOne(query, Comment.class);
        if(one != null){
            if(one.getLike_num() > 0)one.setLike_num(one.getLike_num() - 1);
            else one.setLike_num(0);
            Comment save = mongoTemplate.save(one);
            return true;
        }
        return false;
    }

    private void process(List<Comment> comments) {
        process(comments,true);
    }
    private void process(List<Comment> comments,boolean flag){     /*处理评论，默认为true，清除二级评论*/
        //合并评论
        for (Comment comment : comments) {
            if(comment.getParent_id().equals(""))mergeComment(comments,comment);
        }

        //去除所有二级评论
        if(flag){
            comments.removeIf(comment -> !comment.getParent_id().equals(""));       //合并完成之后，移除所有的二级评论
        }
    }
    private void mergeComment(List<Comment> commentList,Comment comment){
        for (Comment c : commentList) {
            if(Objects.equals(c.getParent_id(), comment.getId())){
                mergeComment(commentList,c);   //先处理子评论
                List<Comment> commentList1 = comment.getCommentList();
                if(commentList1 == null) commentList1 = new ArrayList<>();   //假如评论没有二级评论，则commentList为null，所以额外判断一下
                commentList1.add(c);
                comment.setCommentList(commentList1);

            }
        }
    }
}
