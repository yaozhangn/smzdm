package com.smzdm.service.impl;

import com.smzdm.bean.User;
import com.smzdm.dao.UserMapper;
import com.smzdm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {
    /**
     * 自动注入dao层对象
     */
    @Autowired
    private UserMapper userMapper;

    /**
     *用户注册
     */
    @Transactional
    @Override
    public HashMap register(User user, HttpSession session) {

        HashMap<Object, Object> hashMap = new HashMap<>();

        String username = user.getUsername();
        System.out.println("username 1 = " +username );

        //可用参数校验
        if (username==null || username.isEmpty()){
            hashMap.put("code",1);
            hashMap.put("msgname","用户名不能为空");
        }else{
            User ret = userMapper.selectUserByUsername(username);

            if (ret != null) {
                hashMap.put("code", 1);
                hashMap.put("msgname", "用户名已经被注册");
            } else {
                //执行注册操作
                HashMap<String, Object> map = new HashMap<>();
                map.put("username",username);
                map.put("password",user.getPassword());
                userMapper.insertUserByUsernameAndPassword(map);
                hashMap.put("code", 0);

                //注册成功，保存用户信息
                User user1 = userMapper.selectUserByUsername(username);
                session.setAttribute("user",user1);

            }
        }

        return hashMap;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public User selectUserByUsernameAndPassword(String username, String password) {

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("username",username);
        hashMap.put("password",password);
        return userMapper.selectUserByUsernameAndPassword(hashMap);
    }

    /**
     *根据用户名查询用户
     */
    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    /**
     * 查看用户信息
     * @param id
     * @return
     */
    @Override
    public User selectUserById(int id) {
        return userMapper.selectUserById(id);
    }




}
