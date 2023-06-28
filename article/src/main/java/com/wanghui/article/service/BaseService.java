package com.wanghui.article.service;

import com.wanghui.article.dao.ArticleMapper;
import com.wanghui.common.pojo.Article;
import com.wanghui.common.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BaseService {
    @Autowired
    public ArticleMapper articleMapper;

    @Autowired
    public MongoTemplate mongoTemplate;

    @Autowired
    public CommentService commentService;

    public void packArticles(List<Article> articles){
        for (Article article : articles) {
            packArticle(article,false);
        }
    }
    public void packArticle(Article article,boolean hasComment){          //对article进行包装
        if(hasComment){
            /*封装评论*/
            List<Comment> byArticleId = commentService.findByArticleId(article.getId());
            article.setComments(byArticleId);
        }

        /*封装分类*/
        String articleClass = articleMapper.getArticleClassName(article.getId());
        article.setClass_name(articleClass);
    }
}
