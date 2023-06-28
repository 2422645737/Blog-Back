package com.wanghui.common.pojo;

import com.wanghui.common.valid.AddGroup;
import com.wanghui.common.valid.UpdateGroup;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class User {

    @Id
    @Null(message = "新增不能指定id",groups = {AddGroup.class})
    @NotNull(message = "修改必须指定id",groups = {UpdateGroup.class})
    private String id;

    @NotNull(message = "用户名不能为空",groups = {AddGroup.class, UpdateGroup.class})
    private String username;


    @NotNull(message = "密码不能为空",groups = {AddGroup.class, UpdateGroup.class})
    private String password;

    private String avatar;        //用户头像

    @Email
    private String email;

}
