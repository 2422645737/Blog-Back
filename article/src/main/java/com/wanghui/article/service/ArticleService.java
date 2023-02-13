package com.wanghui.article.service;

import com.wanghui.common.pojo.Article;
import com.wanghui.common.VO.MyParam;
import com.wanghui.common.utils.PageUtils;

public interface ArticleService{
    public PageUtils<Article> findAll(MyParam param,long current, long size); /*获取所有文章*/

    Article getArticleById(String id);        /*根据id查找文章*/

    public PageUtils<Article> getArticleByClassId(String class_id,long current,long size);

    public PageUtils<Article> getArticle(long current,long size);

    boolean save(Article article);    /*保存文章*/

    boolean likeArticle(String aid, int uid);     //文章点赞

    boolean hasLike(String aid, int uid);
}
