package com.wanghui.common.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("article")
@Document(collection = "article")
public class Article implements Serializable {
    @Id
    private String id;

    private String title;
    private String publish_time;
    private int quantity;        //点赞数
    private String type;
    private String cover;
    private String class_name;

    private String summary;      //文章摘要

    private String content;          //文章内容

    private List<String> tags;          //标签

    private List<Comment> comments;       //评论

}
