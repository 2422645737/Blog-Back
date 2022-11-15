package com.wanghui.article.service;

import com.wanghui.article.pojo.Article;
import com.wanghui.common.utils.PageUtils;

public interface ArticleService {
    public PageUtils<Article> findAll(long current, long size); /*获取所有文章*/

    Article getArticleById(Integer id);        /*根据id查找文章*/

    void test();
}
