package com.smzdm.dao;

import com.smzdm.bean.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface NewMapper {

    int insertNews(@Param("news") News news);

    List<News> selectAllNews();

    News selectNewsById(int id);

    void updateNewsCommentCountById(@Param("news") News news);

   /* @Update("update t_news set likeCount=#{likeCount} where id=#{id} ")
    void updateLikeCountById(@Param("id") int newsId, @Param("likeCount") long likeCount);*/

    void updateLikeCountById(HashMap<String, Object> hashMap);
}
