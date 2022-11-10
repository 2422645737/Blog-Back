package com.wanghui.article.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
}
