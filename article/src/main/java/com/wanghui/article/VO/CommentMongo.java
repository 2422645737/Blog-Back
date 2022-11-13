package com.wanghui.article.VO;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "com")
public class CommentMongo {
    @Id
    private String id;
    String content;
}
