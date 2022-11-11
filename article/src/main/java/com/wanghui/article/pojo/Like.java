package com.wanghui.article.pojo;

import lombok.Data;

@Data
public class Like {
    private int article_id;
    private int user_id;
    private String like_time;
}
