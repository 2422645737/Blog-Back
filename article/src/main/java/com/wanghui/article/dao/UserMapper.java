package com.wanghui.article.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Select("select username from user where id = #{id}")
    public String getUserName(String id);

}
