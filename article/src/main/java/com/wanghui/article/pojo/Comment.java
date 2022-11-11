package com.wanghui.article.pojo;

import lombok.Data;

@Data
public class Comment {
    private int article_id;
    private int user_id;
    private String comment_time;
    private String content;
}
