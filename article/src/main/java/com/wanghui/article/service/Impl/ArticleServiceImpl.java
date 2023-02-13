package com.wanghui.article.service.Impl;

import com.wanghui.common.pojo.Article;
import com.wanghui.article.service.ArticleService;
import com.wanghui.article.service.BaseService;
import com.wanghui.common.VO.MyParam;
import com.wanghui.common.utils.MyUtils;
import com.wanghui.common.utils.PageUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ArticleServiceImpl extends BaseService implements ArticleService{
    @Override
    public PageUtils<Article> findAll(MyParam param,long current, long size){        /*查找所有文章并进行分页*/

        //判断查询条件
        int type = 0;  //表示无条件查询
        if(param.getClass_id() != null && !Objects.equals(param.getClass_id(), "0"))type = 1;    //根据分类查询
        else if(param.getTitle() != null) type = 2;   //根据标题查询
        else if(param.getTag() != null) type = 3;     //根据标签查询


        size = (size <= 0) ? PageUtils.DEFAULT_PAGE_SIZE : size;     //对current和size范围判定
        current = (current <= 0) ? 1 : current;
        if(type == 0)return getArticle(current,size);
        else if(type == 1)return getArticleByClassId(param.getClass_id(), current,size);
        else{
            return null;
        }

    }
    @Override
    public Article getArticleById(String id) {              /*根据id查找文章*/
        Query query = new Query(Criteria.where("_id").is(id));
        Article article = mongoTemplate.findOne(query,Article.class);
        if(article != null)packArticle(article);
        return article;
    }

    @Override
    public PageUtils<Article> getArticleByClassId(String class_id,long current,long size) {
        /*根据分类id获取分类名称和数量*/
        String classNameById = articleMapper.getClassNameById(class_id);
        int countByClassId = articleMapper.getCountByClassId(class_id);

        /*查询文章分页*/
        Query query = new Query(Criteria.where("class_name").is(classNameById));
        query.skip((current - 1) * size).limit((int) size);
        List<Article> articles = mongoTemplate.find(query, Article.class);

        packArticles(articles);
        return new PageUtils<>(articles,current,size,countByClassId);
    }

    @Override
    public PageUtils<Article> getArticle(long current, long size) {
        //无条件查询
        Query query = new Query();
        query.skip((current - 1) * size).limit((int)size);
        List<Article> articles = mongoTemplate.find(query, Article.class);
        packArticles(articles);
        //查询总数
        Query q = new Query();
        long count = mongoTemplate.count(query, Article.class);
        return new PageUtils<>(articles, current, size, count);
    }


    @Override
    public boolean save(Article article) {          /*保存文章*/
        Article savedArticle = mongoTemplate.save(article);
        //处理文章的分类，在mysql中注册该分类
        String classId = articleMapper.getClassIdByName(savedArticle.getClass_name()); //获取分类id
        int count = articleMapper.saveArticleWithClass(savedArticle.getId(), classId);
        return count > 0;
    }

    @Override       //文章点赞
    public boolean likeArticle(String aid, int uid) {
        //检查该用是否点赞了该文章
        if(articleMapper.hasLiked(aid,uid) > 0){
            //用户已经点赞了该文章
            return articleMapper.disLikeArticle(aid,uid) > 0;
        }else{
            //插入数据，文章点赞
            return articleMapper.likeArticle(aid,uid, MyUtils.getTime(MyUtils.DETAILTIME)) > 0;
        }
    }

    @Override       //检查当前用户是否点赞该文章
    public boolean hasLike(String aid, int uid) {
        return articleMapper.hasLiked(aid, uid) > 0;
    }
}
