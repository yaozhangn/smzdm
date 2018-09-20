package com.smzdm.controller;

import com.smzdm.bean.EntityType;
import com.smzdm.bean.HostHolder;
import com.smzdm.bean.User;
import com.smzdm.service.LikeService;
import com.smzdm.service.NewService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class LikeController {

    @Autowired
    private LikeService likeService;
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private NewService newService;

    /**
     * 点赞
     */
    @RequestMapping(value = "/like",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public HashMap like(@RequestParam("newsId") int newsId, HttpSession session){

        //在likeKey对应的集合中加入当前用户的id，一条资讯一位用户只能点赞一次
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        long likeCount = likeService.like(userId, EntityType.ENTITY_NEWS,newsId);

        //资讯上更新点赞数
        newService.updateLikeCount(newsId,likeCount);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code",0);
        hashMap.put("msg",likeCount);
        return hashMap;
    }

    /**
     * 当前用户点踩
     * @param newsId
     * @param session
     * @return
     */
    @RequestMapping("/dislike")
    @ResponseBody
    public HashMap disLike(@RequestParam("newsId") int newsId,HttpSession session){

        HashMap<String , Object> hashMap = new HashMap<>();
        //在dislikeKey对应的集合中加入当前用户的id，一条资讯一位用户只能点踩一次
        //获取当前用户
        User user = (User) session.getAttribute("user");
        int id = user.getId();
        long likeCount = likeService.disLike(id,EntityType.ENTITY_NEWS,newsId);
        if (likeCount <= 0){
            likeCount = 0;
        }

        //资讯更新点赞数
        newService.updateLikeCount(newsId,likeCount);
        hashMap.put("code",0);
        hashMap.put("msg",likeCount);
        return hashMap;
    }
}
