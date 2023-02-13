package com.wanghui.user.service;

import com.wanghui.common.pojo.User;

public interface UserService {

    public User login(User user);

    boolean regist(User user);

    User getInfo(String id);
}
