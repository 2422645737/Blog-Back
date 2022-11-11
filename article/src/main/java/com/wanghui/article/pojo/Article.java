package com.wanghui.article.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("article")
public class Article {
    @TableId
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
