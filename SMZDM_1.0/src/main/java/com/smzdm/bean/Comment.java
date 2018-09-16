package com.smzdm.bean;

import java.util.Date;

/**
 * 评论
 */
public class Comment {

    /**
     * 评论id
     */
    private Integer id;
    /**
     * 创建评论的时间
     */
    private Date createdDate;
    /**
     * 评论内容
     */
    private String content;
    private Integer uid;
    private Integer nid;
    /**
     * 外键news
     */
    private News news;
    /**
     * 外键user
     */
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", createdDate='" + createdDate + '\'' +
                ", content='" + content + '\'' +
                ", uid=" + uid +
                ", nid=" + nid +
                ", news=" + news +
                ", user=" + user +
                '}';
    }
}
