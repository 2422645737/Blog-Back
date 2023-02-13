package com.wanghui.article.controller;

import com.wanghui.common.pojo.Article;
import com.wanghui.article.service.ArticleService;
import com.wanghui.common.VO.MyParam;
import com.wanghui.common.utils.PageUtils;
import com.wanghui.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /*
    * @Descirption 获取所有文章，包含筛选条件
    * @Param
    * 1、class_id    表示按照分类查询文章
    * 2、title       表示按照标题查询文章
    * 3、tag         表示按照标签查询所有文章
    * 4、均为空       表示查询所有文章
    * */
    @GetMapping("articles")
    public R findAll(MyParam param){
        PageUtils<Article> all = articleService.findAll(param,param.getCurrent(),0);
        return R.ok().put("articles",all);
    }

    @GetMapping("/{id}")      /*通过id获取文章*/
    public R getArticleById(@PathVariable String id){
        Article articleById = articleService.getArticleById(id);
        return R.ok().put("article",articleById);
    }

    @PostMapping("/save")        /*保存文章*/
    public R saveArticle(@RequestBody Article article){
        System.out.println(article);
        boolean save = articleService.save(article);
        return save ? R.ok() : R.error();
    }

    @PostMapping("like")       //文章点赞/取消点赞
    public R likeArticle(@RequestParam String aid,@RequestParam int uid){
        boolean ok = articleService.likeArticle(aid,uid);
        return ok ? R.ok() : R.error();
    }

    @GetMapping("has_like")     //检查当前用户是否点赞该文章
    public R hasLike(@RequestParam String aid,@RequestParam int uid){
        boolean ok = articleService.hasLike(aid,uid);
        return ok ? R.ok() : R.error();
    }
}
