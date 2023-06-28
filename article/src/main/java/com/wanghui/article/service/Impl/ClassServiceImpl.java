package com.wanghui.article.service.Impl;

import com.wanghui.article.service.BaseService;
import com.wanghui.article.service.ClassService;
import javafx.util.Pair;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassServiceImpl extends BaseService implements ClassService {
    @Override
    public List<Map<String, String>> getAllClass() {
        /*获取所有分类的名字和id*/
        List<Map<String, String>> allClass = articleMapper.getAllClass();

        /*统计数量*/
        for (Map<String, String> aClass : allClass) {
            int count = articleMapper.getCountByClassId(aClass.get("id"));
            aClass.put("count",String.valueOf(count));
        }
        return allClass;
    }

    @Override
    public boolean save(String class_name) {
        List<Map<String, String>> allClass = articleMapper.getAllClass();
        //检查是否重复
        for (Map<String, String> aClass : allClass) {

        }
        //找到最后一个，直接在他的基础上+1，就是新的标签id
        int max = -1;
        for (Map<String, String> aClass : allClass) {
            int id = -1;
            for (String s : aClass.keySet()) {
                id = Integer.parseInt(s);
            }
            max = Math.max(max,id);
        }
        max++;
        articleMapper.saveClass(String.valueOf(max),class_name);
        return false;
    }
}
