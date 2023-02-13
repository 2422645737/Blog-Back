package com.wanghui.article.service;

import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public interface ClassService {

    List<Map<String,String>> getAllClass();

    boolean save(String class_name);
}
