package com.wanghui.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanghui.common.pojo.User;
import com.wanghui.user.dao.UserMapper;
import com.wanghui.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User login(User user) {        //用户登录
        //数据库查询这个用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        queryWrapper.eq("password",user.getPassword());
        User user1 = userMapper.selectOne(queryWrapper);
        if(user1 != null)user1.setPassword(null);     //设置密码为空，避免前端获取到密码
        return user1;
    }

    @Override
    public boolean regist(User user) {     //用户注册
        return false;
    }

    @Override
    public User getInfo(String id) {        //获取用户信息
        User info = userMapper.getInfo(id);
        if(info != null)packInfo(info);
        return info;
    }

    private void packInfo(User user){      //包装信息，避免泄露用户重要信息
        user.setPassword(null);     //设置密码不可见
    }
}
