package com.wanghui.article.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@TableName("article")
@Document(collection = "article")
public class Article {
    @TableId
    @Field(value = "article_id")
    private int id;

    private String title;
    private String publish_time;
    private int quantity;
    private String type;
    private String cover;

    @TableField(exist = false)
    private String content;          //文章内容

    @TableField(exist = false)
    private List<String> tags;          //标签

    @TableField(exist = false)
    private List<Comment> comments;       //评论

    @TableField(exist = false)
    private List<Like> likes;           //点赞
}
