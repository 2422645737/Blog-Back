package com.wanghui.article.service.Impl;

import com.wanghui.article.dao.ArticleMapper;
import com.wanghui.article.pojo.Article;
import com.wanghui.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    public ArticleMapper articleMapper;
    @Override
    public List<Article> findAll(){
        return articleMapper.selectList(null);
    }
}
