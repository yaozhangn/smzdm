package com.smzdm.bean;

public class CommentVo {

    private Integer nid;

    private Comment comment;

    private User user;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CommentVo{" +
                "nid=" + nid +
                ", comment=" + comment +
                ", user=" + user +
                '}';
    }
}
