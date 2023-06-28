package com.wanghui.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtils {
    public static final String NODETAILTIME = "yyyy-MM-dd";
    public static final String DETAILTIME = "yyyy-MM-dd hh:mm:ss";
    public static String getTime(String format){       //获取当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }
}
