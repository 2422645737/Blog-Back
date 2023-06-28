package com.wanghui.common.utils;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class PageUtils<T> {
    public static final long DEFAULT_PAGE_SIZE = 10L;

    protected long size = 10;         //默认分页大小 = 10条
    protected long current;         //当前页
    protected long total;          //总条目
    protected List<T> records;     //记录内容


    public PageUtils(){

    }

    //仅针对于mongodb数据进行封装
    public PageUtils(List<T> items,long current, long size,long total){
        this.setCurrent(current);
        this.setSize(size);
        this.setTotal(total);
        this.setRecords(items);
    }
    public PageUtils(Page<T> page){
        this.current = page.getCurrent();
        this.size = page.getSize();
        this.total = page.getTotal();
        this.records = page.getRecords();
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
