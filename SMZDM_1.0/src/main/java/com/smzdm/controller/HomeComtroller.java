package com.smzdm.controller;

import com.smzdm.bean.News;
import com.smzdm.bean.NewsVo;
import com.smzdm.bean.User;
import com.smzdm.service.NewService;
import com.smzdm.service.UserService;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.LinkedList;
import java.util.List;

@Controller
public class HomeComtroller {

    /**
     * 自动注入service对象
     */
    @Autowired
    private NewService newService;
    @Autowired
    private UserService userService;

    /**
     * 访问home页面
     * 查询news资讯
     * @return
     */
    @RequestMapping("/")
    public ModelAndView init(ModelAndView modelAndView){

        List<NewsVo> newsVos = new LinkedList<>();
        NewsVo vo = null;

        List<News> newsList = newService.selectAllNews();

        for (News news : newsList) {
            String name = news.getName();
            User user = userService.selectUserByUsername(name);
            vo = new NewsVo();
            vo.setUser(user);
            vo.setNews(news);
            vo.setLike(0);
            newsVos.add(vo);
        }

        modelAndView.addObject("vos",newsVos);
        modelAndView.addObject("date",new DateTool());
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
