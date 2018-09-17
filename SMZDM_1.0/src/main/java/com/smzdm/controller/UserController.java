package com.smzdm.controller;

import com.smzdm.bean.User;
import com.smzdm.service.UserService;
import com.smzdm.utils.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping(value = "/reg/",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public HashMap register(User user, HttpSession session){

        HashMap<String, Object> hashMap = userService.register(user,session);

        return hashMap;
    }

    /**
     *用户登录
     */
    @RequestMapping("/login/")
    @ResponseBody
    public MsgUtils login(User user, Integer rember, HttpSession session, HttpServletResponse response){

        //参数校验
        String password = user.getPassword();
        String username = user.getUsername();
        if (username == null || username.isEmpty()){
            return new MsgUtils(1,"用户名不能为空",null);
        }else if (password ==null || password.isEmpty()){
           return new MsgUtils(1,null,"密码不能为空");
        }

        User user1 = userService.selectUserByUsernameAndPassword(username,password);

        if (user1 != null){
            session.setAttribute("user",user1);
            if(rember.equals(1)){
                //记住用户登录信息
                //创建cookie对象
                Cookie namecookie = new Cookie("username",username);
                Cookie passwordcookie = new Cookie("password", password);
                //设置cookie的有效期，一周
                namecookie.setMaxAge(60*60*24*7);
                passwordcookie.setMaxAge(60*60*24*7);
                //设置cookie的路径，保证首页可以读取
                passwordcookie.setPath("/");
                namecookie.setPath("/");
                response.addCookie(namecookie);
                response.addCookie(passwordcookie);
            }
            return new MsgUtils(0,null,null);
        }else{
            //判断用户名错误还是密码错误
           User user2 = userService.selectUserByUsername(username);
           if(user2 != null){
               return new MsgUtils(1, null, "密码错误");
           }else{
                return new MsgUtils(1, "用户名不正确", null);
           }
        }
    }

    /**
     * 查看用户信息
     */
    @RequestMapping("/user/{id}")
    public ModelAndView findUserMsg(User user,ModelAndView model){

        User user1 = userService.selectUserById(user.getId());
        model.addObject("user",user1);
        model.setViewName("personal");

        return model;
    }

    /**
     * 用户注销登录
     */
    @RequestMapping("/logout/")
    public String logout(HttpSession session,ModelAndView modelAndView){
        if (session!=null){
            session.invalidate();
        }
        return "home";
    }
}
