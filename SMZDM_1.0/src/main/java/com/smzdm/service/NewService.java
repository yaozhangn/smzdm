package com.smzdm.service;

import com.smzdm.bean.News;

import java.util.List;

public interface NewService {
    int addNews(News news);


    List<News> selectAllNews();

    News selectNewsById(int id);

    void updateLikeCount(int newsId, long likeCount);
}
