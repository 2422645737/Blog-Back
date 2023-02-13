package com.wanghui.common.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {

    @Id
    private String id;

    private String username;
    private String password;
    private String avatar;        //用户头像


}
