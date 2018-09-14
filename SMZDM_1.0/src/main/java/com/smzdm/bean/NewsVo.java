package com.smzdm.bean;

import jdk.nashorn.internal.ir.debug.PrintVisitor;

public class NewsVo {

    private News news;

    private User user;

    private Integer like;

    public NewsVo() {
    }

    public NewsVo(News news, User user, Integer like) {
        this.news = news;
        this.user = user;
        this.like = like;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "NewsVo{" +
                "news=" + news +
                ", user=" + user +
                ", like=" + like +
                '}';
    }
}
