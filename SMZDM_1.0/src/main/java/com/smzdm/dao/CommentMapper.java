package com.smzdm.dao;

import com.smzdm.bean.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> selectCommentListByNid(Integer id);

    void insertComment(@Param("comment") Comment comment);
}
