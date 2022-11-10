package com.wanghui.article.controller;

import com.wanghui.article.pojo.Article;
import com.wanghui.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("findAll")
    public void findAll() throws IOException {
        List<Article> all = articleService.findAll();
        for (Article article : all) {
            System.out.println(article);
        }
    }

}
