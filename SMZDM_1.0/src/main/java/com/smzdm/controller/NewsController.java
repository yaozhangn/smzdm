package com.smzdm.controller;


import com.smzdm.bean.News;
import com.smzdm.bean.User;
import com.smzdm.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 资讯news控制层
 */
@Controller
public class NewsController {

    /**
     * 自动注入service对象
     */
    @Autowired
    private NewService newService;

    /**
     * 分享资讯
     */
    @RequestMapping(value = "/user/addNews/",method = RequestMethod.POST)
    @ResponseBody
    public HashMap addNews(News news,HttpSession session){

        HashMap<String, Object> hashMap = new HashMap<>();

        //设置当前的评论数，创建时间,点赞数
        news.setCommentCount(0);
        Date date = getCreatedDate();
        news.setCreatedDate(date);
        news.setLikeCount(0);

        //从session获取当前用户信息
        User user = (User) session.getAttribute("user");
        news.setName(user.getUsername());

        //插入一条资讯
         int ret = newService.addNews(news);
         if (ret ==1){
             //插入成功，且插入的是一条资讯
             hashMap.put("status",200);
         }

        return hashMap;
    }

    //将日期格式转换，便于据此格式查询数据库中的date
    private Date getCreatedDate() {

        Date createdDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(createdDate);
        Date parse = null;

        try {
            parse = sdf.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parse;
    }

}
