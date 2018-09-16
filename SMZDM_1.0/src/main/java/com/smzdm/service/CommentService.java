package com.smzdm.service;

import com.smzdm.bean.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> selectCommentListByNid(Integer id);

    void addComment(Comment comment);
}
