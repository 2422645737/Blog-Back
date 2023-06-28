package com.wanghui.article.service;

import com.wanghui.common.pojo.Article;
import com.wanghui.common.VO.MyParam;
import com.wanghui.common.utils.PageUtils;

import java.util.List;

public interface ArticleService{

    /**************查询相关*****************/
    public PageUtils<Article> findAll(MyParam param); /*获取所有文章*/
    Article getArticleById(String id);        /*根据id查找文章*/
    public PageUtils<Article> getArticleByClassId(String class_id,long current,long size);
    public PageUtils<Article> getArticleByTitle(String name,long current,long size);
    public PageUtils<Article> getArticle(long current,long size);
    public PageUtils<Article> getArticleByContent(String keyword,long current,long size);
    public PageUtils<Article> getArticleByTag(String tag,long current,long size);



    /****************操作相关******************/
    boolean save(Article article);    /*保存文章*/

    boolean likeArticle(String aid, int uid);     //文章点赞
    boolean hasLike(String aid, int uid);

    List<String> getTags();
}
