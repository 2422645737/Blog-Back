package com.wanghui.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanghui.common.pojo.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where id = #{id}")
    public User getInfo(String id);
}
