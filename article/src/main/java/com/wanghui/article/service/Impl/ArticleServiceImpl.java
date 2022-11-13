package com.wanghui.article.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanghui.article.VO.CommentMongo;
import com.wanghui.article.dao.ArticleMapper;
import com.wanghui.article.pojo.Article;
import com.wanghui.article.pojo.Comment;
import com.wanghui.article.pojo.Like;
import com.wanghui.article.service.ArticleService;
import com.wanghui.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    public ArticleMapper articleMapper;

    @Autowired
    public MongoTemplate mongoTemplate;

    private void packArticles(List<Article> articles){
        for (Article article : articles) {
            packArticle(article);
        }
    }
    private void packArticle(Article article){          //对article进行包装
        //包装标签
        List<String> tagsById = articleMapper.getTagsById(article.getId());
        article.setTags(tagsById);

        //包装评论

//        List<Comment> commentsById = articleMapper.getCommentsById(article.getId());
//        article.setComments(commentsById);

        //包装点赞
        List<Like> likesById = articleMapper.getLikesById(article.getId());
        article.setLikes(likesById);

    }
    @Override
    public PageUtils<Article> findAll(long current, long size){        /*查找所有文章并进行分页*/
        size = (size <= 0) ? PageUtils.DEFAULT_PAGE_SIZE : size;     //对current和size范围判定
        current = (current <= 0) ? 1 : current;
        Page<Article> articlePage = articleMapper.selectPage(new Page<>(current,size), null);

        packArticles(articlePage.getRecords());

        return new PageUtils<>(articlePage);
    }
    @Override
    public Article getArticleById(Integer id) {              /*根据id查找文章*/
        Article article = articleMapper.selectById(id);
        return article;
    }

    @Override
    public CommentMongo test() {
        //测试mongoDB
        Query query = new Query(Criteria.where("content").is("hello"));
        CommentMongo one = mongoTemplate.findOne(query, CommentMongo.class);
        System.out.println(one);
        return null;
    }
}
