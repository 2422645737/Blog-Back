package com.wanghui.common.VO;

import lombok.Data;

@Data
public class RequestParam {
    private long current;    //当前分页
    private long size;       //分页大小
}
