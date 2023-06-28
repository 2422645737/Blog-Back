package com.wanghui.article.controller;

import com.wanghui.article.service.ClassService;
import com.wanghui.common.utils.R;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("class")
public class ClassController {
    @Autowired
    ClassService classService;

    @GetMapping("/count")       /*获取所有的分类名称以及该分类下文章数量，便于前台显示目录*/
    public R getAllClass(){
        List<Map<String, String>> allClass = classService.getAllClass();
        return R.ok().put("class",allClass);
    }

    @PostMapping("/save")      //新建分类
    public R save(@RequestBody String class_name){
        boolean save = classService.save(class_name);
        return R.ok();
    }

}
