package com.smzdm.bean;

import java.util.Date;

/**
 * 资讯类
 */
public class News {

    /**
     * 资讯编号
     */
    private int id;
    /**
     * 资讯标题
     */
    private String title;

    /**
     * 资讯的点赞数
     */
    private int likeCount;

    /**
     *资讯的创建时间
     */
    private Date createdDate;

    /**
     * 资讯图片地址
     */
    private String image;

    /**
     * 资讯评论数
     */
    private int commentCount;
    /**
     * 资讯链接
     */
    private String link;

    /**
     * 资讯发布者名称
     */
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", likeCount=" + likeCount +
                ", createdDate=" + createdDate +
                ", image='" + image + '\'' +
                ", commentCount=" + commentCount +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
