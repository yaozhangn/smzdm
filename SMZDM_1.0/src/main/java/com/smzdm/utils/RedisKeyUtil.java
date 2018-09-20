package com.smzdm.utils;

/**
 * 点击点赞或者踩的时候，会根据所属的资讯id生成一个对应的点赞（likeKey）或者踩（dislikeKey）的set集合名称key值
 * 将对应的点赞或者踩的用户id视为value存入set集合key中
 */
public class RedisKeyUtil {

    private static String SPLIT = ":";
    private static String BIZ_LIKE = "LIKE";
    private static String BIZ_DISLIKE = "DISLIKE";

    /**
     * 点赞时产生key,如在newId为2 的资讯上点赞，会产生key： LIKE:ENTITY_NEWS:2
     * @param entityId
     * @param entityType
     * @return
     */
    public static String getLikeKey(int entityId,int entityType){
        return BIZ_LIKE + SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }

    /**
     * 踩时，产生对应key
     * 如，在newsId的资讯点踩，会产生key： DISLIKE: ENTITY_NEWS:2
     * @param entityId
     * @param entityType
     * @return
     */
    public static String getDisLikeKey(int entityId, int entityType){
        return BIZ_DISLIKE +SPLIT + String.valueOf(entityType) + SPLIT + String.valueOf(entityId);
    }
}
