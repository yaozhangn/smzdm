package com.smzdm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Init {

    /**
     * 绕开拦截，直接访问 home页面
     */
    @RequestMapping("/")
    public String init(){
        return "home";
    }
}
