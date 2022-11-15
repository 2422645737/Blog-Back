package com.wanghui.article.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "comment")
public class Comment {
    @Id
    private String id;

    private int article_id;         //文章ID
    private int user_id;        //用户ID
    private String username;       //用户名
    private String comment_time;       //评论时间
    private String content;      //评论内容
    private String parent_id;   //上级id
    private int like_num;        //点赞数量

    @Transient
    private List<Comment> commentList;     //评论的所有回复

}
