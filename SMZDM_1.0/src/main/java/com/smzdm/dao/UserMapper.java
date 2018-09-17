package com.smzdm.dao;

import com.smzdm.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;

@Mapper
public interface UserMapper {

    @Select("select * from t_user where username=#{username}")
    User selectUserByUsername(String username);


    void insertUserByUsernameAndPassword(@Param("user") User user);


    User selectUserByUsernameAndPassword(HashMap<String, Object> hashMap);

    User selectUserById(int id);
}
