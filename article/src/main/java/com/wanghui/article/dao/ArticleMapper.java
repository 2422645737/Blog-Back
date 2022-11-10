package com.wanghui.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanghui.article.pojo.Article;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticleMapper extends BaseMapper<Article> {
}
