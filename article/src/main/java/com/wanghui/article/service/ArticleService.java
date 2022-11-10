package com.wanghui.article.service;

import com.wanghui.article.pojo.Article;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface ArticleService {
    public List<Article> findAll() throws IOException;
}
