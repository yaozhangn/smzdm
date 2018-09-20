package com.smzdm.service.impl;

import com.smzdm.service.LikeService;
import com.smzdm.utils.JedisAdapter;
import com.smzdm.utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {


    @Autowired
    private JedisAdapter jedisAdapter;

    /**
     * 当前用户点赞后，被点赞的用户like集合中就会加上一个该点赞用户的id
     * @param userId
     * @param entityType
     * @param entityId
     * @return
     */
    @Override
    public long like(int userId, int entityType, int entityId) {
        //在当前的news上点赞后获取key  LIKE:ENTITY_NEWS : entityId
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        //在当前news的喜欢集合上增加点赞用户的id
        jedisAdapter.sadd(likeKey, String.valueOf(userId));

        //获取当前用户点踩的dislikeKey
        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
        //在当前资讯点踩的集合中减去点赞用户
        jedisAdapter.srem(disLikeKey, String.valueOf(userId));

        //返回点赞的数量
        return jedisAdapter.scard(likeKey);
    }

    /**
     * 当前用户对资讯点踩后，该资讯的disLikeKey集合中会增加当前用户的id
     * @param userId
     * @param entityType
     * @param entityId
     * @return
     */
    @Override
    public long disLike(int userId, int entityType, int entityId) {

        //点踩之后，资讯的点踩集合中增加该用户的id信息
        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
        jedisAdapter.sadd(disLikeKey, String.valueOf(userId));

        //点赞集合likeKey中删除该用户的id
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        jedisAdapter.srem(likeKey, String.valueOf(userId));

        //返回点赞数
        return jedisAdapter.scard(likeKey);
    }
}
