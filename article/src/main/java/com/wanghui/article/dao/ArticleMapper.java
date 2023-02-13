package com.wanghui.article.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanghui.common.pojo.Article;
import javafx.util.Pair;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("select class_name from article_class,class " +
            "where article_class.code = class.id " +
            "and article_class.id = #{id}")  /*根据文章id获取分类名称*/
    public String getArticleClass(String id);

    @Select("select class_name from class where id = #{id}")      /*根据分类id获取分类名称*/
    public String getClassNameById(String id);

    @Select("select id from class where class_name = #{class_name}")      /*根据分类名称获取分类id*/
    public String getClassIdByName(String class_name);


    @Select("select count(*) from article_class where code = #{id}")        /*获取某个分类下的文章数量*/
    public int getCountByClassId(String id);

    @Select("select * from class")          //搜索所有的分类以及id
    public List<Map<String,String>> getAllClass();

    @Insert("insert into class values(#{id},#{class_name}))")
    public String saveClass(String id,String class_name);

    @Insert("insert into article_class values(#{id},#{code})")
    public Integer saveArticleWithClass(@Param("id") String id, @Param("code") String code);

    @Insert("insert into article_like values(#{aid},#{uid},#{time})")        //点赞文章
    public int likeArticle(@Param("aid") String aid,@Param("uid") int uid,@Param("time") String time);

    @Delete("delete from article_like where aid = #{aid} and uid = #{uid}")        //取消点赞文章
    public int disLikeArticle(@Param("aid") String aid,@Param("uid") int uid);
    @Select("select count(*) from article_like where aid = #{aid} and uid = #{uid}")   //检查当前用户是否点赞该文章
    public int hasLiked(@Param("aid") String aid,@Param("uid") int uid);
}

