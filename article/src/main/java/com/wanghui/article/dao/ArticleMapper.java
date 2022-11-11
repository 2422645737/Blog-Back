package com.wanghui.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanghui.article.pojo.Article;
import com.wanghui.article.pojo.Comment;
import com.wanghui.article.pojo.Like;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    @Select("select tag from article_tag where article_id = #{id}")   /*根据id查找所有标签*/
    public List<String> getTagsById(int id);

    @Select("select * from article_comment where article_id = #{id}")
    public List<Comment> getCommentsById(int id);     /*根据文章id查找所有评论*/

    @Select("select * from article_like where article_id = #{id}")         /*根据id查找所有点赞*/
    public List<Like> getLikesById(int id);
}

