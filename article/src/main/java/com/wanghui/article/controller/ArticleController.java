package com.wanghui.article.controller;

import com.wanghui.article.pojo.Article;
import com.wanghui.article.service.ArticleService;
import com.wanghui.article.service.CommentService;
import com.wanghui.common.VO.RequestParam;
import com.wanghui.common.utils.PageUtils;
import com.wanghui.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @GetMapping("articles/comment")
    public R findComments(){
        articleService.test();
        return R.ok();
    }

    @GetMapping("articles")   /*获取所有文章，用于测试*/
    public R findAll(RequestParam param){

        PageUtils<Article> all = articleService.findAll(param.getCurrent(),param.getSize());
        return R.ok().put("articles",all);
    }

    @GetMapping("articles/{id}")      /*通过id获取文章*/
    public R getArticleById(@PathVariable Integer id){
        Article articleById = articleService.getArticleById(id);
        return R.ok().put("article",articleById);
    }

}
