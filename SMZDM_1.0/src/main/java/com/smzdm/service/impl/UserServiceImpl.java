package com.smzdm.service.impl;

import com.smzdm.bean.User;
import com.smzdm.dao.UserMapper;
import com.smzdm.service.UserService;
import org.apache.commons.lang.StringUtils;
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
    public HashMap register(User user,HttpSession session) {

        HashMap<Object, Object> hashMap = new HashMap<>();

        //参数校验
        if (StringUtils.isBlank(user.getUsername())){
            //判断用户名为空
            hashMap.put("msgname","用户名不能为空");
            return hashMap;
        }

        if (StringUtils.isBlank(user.getPassword())){
            //判断密码为空
            hashMap.put("msgpwd","密码不能为空");
            return hashMap;
        }

        User user1 = userMapper.selectUserByUsername(user.getUsername());
        if (user1 != null){
            //用户名已经存在
            hashMap.put("msgname","用户名已存在");
            return hashMap;
        }

        //https://my-user-head.oss-cn-shenzhen.aliyuncs.com/1.jpg
        String headUrl = String.format("https://my-user-head.oss-cn-shenzhen.aliyuncs.com/%s.jpg", (int)(Math.random() * 6) + 1);
        user.setHeadUrl(headUrl);
        //注册用户
        userMapper.insertUserByUsernameAndPassword(user);
        hashMap.put("code",0);

        //注册成功之后，自动登录
        HashMap<String, Object> map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("password",user.getPassword());
        User user2 = userMapper.selectUserByUsernameAndPassword(map);
        if (user2 !=null){
            //存在该用户，保存用户信息
            session.setAttribute("user",user2);
        }else {
            hashMap.put("msgpwd","注册失败");
            return hashMap;
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
