package com.wanghui.article.service.Impl;

import com.wanghui.common.pojo.Article;
import com.wanghui.article.service.ArticleService;
import com.wanghui.article.service.BaseService;
import com.wanghui.common.VO.MyParam;
import com.wanghui.common.utils.MyUtils;
import com.wanghui.common.utils.PageUtils;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class ArticleServiceImpl extends BaseService implements ArticleService{
    @Override      //获取所有文章（包含检索条件）
    public PageUtils<Article> findAll(MyParam param){        /*查找所有文章并进行分页*/
        //判断查询条件
        int type = 0;  //表示无条件查询
        if(param.getClass_id() != null && !Objects.equals(param.getClass_id(), "0")){
            type = 1;    //根据分类查询
        }
        else if(param.getTitle() != null){
            type = 2;   //根据标题查询
        }
        else if(param.getTag() != null){
            type = 3;     //根据标签查询
        }
        checkParam(param);
        if(type == 0){
            return getArticle(param.getCurrent(),param.getSize());
        }
        else if(type == 1){
            return getArticleByClassId(param.getClass_id(), param.getCurrent(),param.getSize());
        }
        else if(type == 2){
            return getArticleByTitle(param.getTitle(),param.getCurrent(),param.getSize());
        }
        else {
            return getArticleByTag(param.getTag(),param.getCurrent(),param.getSize());
        }

    }
    @Override   //文章id --> 文章
    public Article getArticleById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Article article = mongoTemplate.findOne(query,Article.class);
        if(article != null)packArticle(article,true);
        return article;
    }

    /**
     * 通过分类id获取文章
     *
     * @param class_id 分类id
     * @param current  当前页
     * @param size     页面大小
     * @return {@link PageUtils}<{@link Article}>
     */
    @Override          //分类id --> 文章
    public PageUtils<Article> getArticleByClassId(String class_id,long current,long size) {
        /*根据分类id获取分类名称*/
        String classNameById = articleMapper.getClassNameById(class_id);
        Query query = new Query(Criteria.where("class_name").is(classNameById));
        return getPage(query,current,size);
    }


    @Override        //文章名字 --> 文章
    public PageUtils<Article> getArticleByTitle(String name, long current, long size) {
        Query query = new Query(Criteria.where("title").regex("^.*" + name + ".*$","i"));  //模糊查询
        return getPage(query,current,size);
    }
    @Override           //无条件查询文章
    public PageUtils<Article> getArticle(long current, long size) {
        return getPage(new Query(),current,size);
    }

    @Override        //文章内容 --> 文章
    public PageUtils<Article> getArticleByContent(String keyword,long current, long size) {
        Query query = new Query(Criteria.where("content").regex("^.*" + keyword + ".*$","i"));  //模糊查询
        return getPage(query,current,size);
    }

    @Override         //标签 --> 文章
    public PageUtils<Article> getArticleByTag(String tag,long current, long size) {
        String queryString = "{tags:'" + tag + "'}";
        Query query = new BasicQuery(queryString);
        return getPage(query,current,size);
    }


    @Override       //保存文章
    public boolean save(Article article) {
        Article savedArticle = mongoTemplate.save(article);
        //处理文章的分类，在mysql中注册该分类
        String classId = articleMapper.getClassIdByName(savedArticle.getClass_name()); //获取分类id
        int count = articleMapper.saveArticleWithClass(savedArticle.getId(), classId);
        return count > 0;
    }

    /**
     * 点赞文章
     *
     * @param aid 文章id
     * @param uid 用户id
     * @return boolean 点赞是否成功
     */
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

    @Override         //获取所有标签
    public List<String> getTags() {
        Query query = new BasicQuery("{}","{'tags':1}");
        List<Article> articles = mongoTemplate.find(query, Article.class);
        Set<String> tagsSet = new HashSet<>();
        for (Article article : articles) {
            tagsSet.addAll(article.getTags());
        }
        return new ArrayList<>(tagsSet);
    }


    //参数校验
    private void checkParam(MyParam param){
        long size = param.getSize();
        long current = param.getCurrent();
        size = (size <= 0) ? PageUtils.DEFAULT_PAGE_SIZE : size;     //对current和size范围判定
        current = (current <= 0) ? 1 : current;
        param.setSize(size);
        param.setCurrent(current);
    }

    //mongodb获取分页内容
    private PageUtils<Article> getPage(Query query,long current,long size){
        long count = mongoTemplate.count(query, Article.class);     //查询数量
        query.skip((current - 1) * size).limit((int) size);
        List<Article> articles = mongoTemplate.find(query, Article.class);
        packArticles(articles);
        return new PageUtils<>(articles,current,size,count);
    }
}
