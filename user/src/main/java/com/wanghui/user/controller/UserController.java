package com.wanghui.user.controller;

import com.wanghui.common.pojo.User;
import com.wanghui.common.utils.R;
import com.wanghui.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("regist")
    public R regist(@RequestBody User user){
        boolean ok = userService.regist(user);
        return ok ? R.ok() : R.error();
    }
    @PostMapping("login")
    public R login(@RequestBody  User user){       //用户登录功能，参数为用户名和密码
        User login = userService.login(user);
        System.out.println(user);
        return login == null ? R.error() : R.ok().put("user",login);
    }

    @GetMapping("info/{id}")
    public R getInfo(@PathVariable String id){       //根据用户id获取用户信息

        User info = userService.getInfo(id);
        return R.ok().put("userInfo",info);
    }

}
