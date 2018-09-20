package com.smzdm.service;

public interface LikeService {
    long like(int id, int entityNews, int newsId);

    long disLike(int id, int entityNews, int newsId);
}
