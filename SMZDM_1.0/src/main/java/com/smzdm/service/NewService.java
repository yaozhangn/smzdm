package com.smzdm.service;

import com.smzdm.bean.News;

import java.util.List;

public interface NewService {
    int addNews(News news);


    List<News> selectAllNews();

}
