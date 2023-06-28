package com.wanghui.common.VO;

import lombok.Data;

@Data
public class MyParam {
    private long current;    //当前分页
    private long size;    //每一页的数量
    //关于文章的参数
    private String class_id;      //文章分类id，当使用分类查询文章时，用来接收参数

    private String title;

    private String tag;
}
