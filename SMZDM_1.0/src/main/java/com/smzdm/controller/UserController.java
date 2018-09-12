package com.smzdm.controller;

import com.smzdm.bean.User;
import com.smzdm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 用户控制层
 */
@Controller
public class UserController {

    /**
     * 自动注入service对象
     */
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("/reg/")
    @ResponseBody
    public HashMap register(User user,HttpSession session){

        HashMap<String, Object> hashMap = userService.register(user,session);

        return hashMap;
    }

    /**
     *用户登录
     */
    @RequestMapping("/login/")
    @ResponseBody
    public HashMap login(User user, String rember, HttpSession session){
        HashMap<String, Object> hashMap = new HashMap<>();

        //参数校验
        String password = user.getPassword();
        String username = user.getUsername();
        if (username == null || username.isEmpty()){
            hashMap.put("code",1);
            hashMap.put("msgname","用户名不能为空");
            return hashMap;
        }else if (password ==null || password.isEmpty()){
            hashMap.put("msgpwd","密码不能为空");
            return hashMap;
        }

        User user1 = userService.selectUserByUsernameAndPassword(username,password);
        hashMap.put("code",0);
        if (user1 != null){
            session.setAttribute("user",user1);
        }else{
            //判断用户名错误还是密码错误
           User user2 = userService.selectUserByUsername(username);
           if(user2 != null){
               hashMap.put("msgpwd","密码错误");
           }else{
               hashMap.put("msgname","用户名不正确");
           }
        }
        return hashMap;
    }
}
