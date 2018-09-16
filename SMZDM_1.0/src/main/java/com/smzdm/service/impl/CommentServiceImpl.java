package com.smzdm.service.impl;

import com.smzdm.bean.Comment;
import com.smzdm.bean.News;
import com.smzdm.dao.CommentMapper;
import com.smzdm.dao.NewMapper;
import com.smzdm.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    /**
     * 自动注入dao对象
     */
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private NewMapper newMapper;

    /**
     * 根据nid查询评论
     * @param id
     * @return
     */
    @Override
    public List<Comment> selectCommentListByNid(Integer id) {
        return commentMapper.selectCommentListByNid(id);
    }

    /**
     * 新增一个评论
     * @param comment
     */
    @Transactional
    @Override
    public void addComment(Comment comment) {

        //增加一个评论项
        commentMapper.insertComment(comment);

        //资讯评论数加一,可修改SQL语句
        News news = newMapper.selectNewsById(comment.getNid());
        int commentCount = news.getCommentCount()+1;
        news.setCommentCount(commentCount);
        newMapper.updateNewsCommentCountById(news);
    }
}
