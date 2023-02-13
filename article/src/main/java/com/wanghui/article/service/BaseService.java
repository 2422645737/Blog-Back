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
            packArticle(article);
        }
    }
    public void packArticle(Article article){          //对article进行包装
        //包装评论
        /*封装评论*/
        List<Comment> byArticleId = commentService.findByArticleId(article.getId());
        article.setComments(byArticleId);


        /*封装分类*/
        String articleClass = articleMapper.getArticleClass(article.getId());
        article.setClass_name(articleClass);
    }

}
