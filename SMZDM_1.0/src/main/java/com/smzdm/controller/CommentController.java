package com.smzdm.controller;

import com.smzdm.bean.Comment;
import com.smzdm.bean.User;
import com.smzdm.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 评论控制层
 */
@Controller
public class CommentController {

    /**
     * 自动注入service
     */
    @Autowired
    private CommentService commentService;

    /**
     *增加评论
     */
    @Transactional
    @RequestMapping("/addComment")
    public ModelAndView addComment(Integer newsId, HttpSession session, Comment comment, ModelAndView modelAndView){

        comment.setNid(newsId);
        //获取当前时间
        Date creatDate = getCreatedDate();
        comment.setCreatedDate(creatDate);

        //获取当前用户id
        User user = (User) session.getAttribute("user");
        System.out.println("user =" +user);
        comment.setUid(user.getId());

        //增加一个评论
        commentService.addComment(comment);
        modelAndView.setViewName("redirect:/news/" + newsId);
        return modelAndView;
    }

    //将日期格式转换，便于据此格式查询数据库中的date
    private Date getCreatedDate() {

        Date createdDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
