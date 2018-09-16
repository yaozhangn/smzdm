package com.smzdm.service.impl;

import com.smzdm.bean.News;
import com.smzdm.dao.NewMapper;
import com.smzdm.service.NewService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewServiceImpl implements NewService {

    /**
     * 自动注入dao对象
     */
    @Autowired
    private NewMapper newMapper;

    /**
     *新增一条资讯
     * @param news 资讯参数
     * @return 返回插入的资讯条数
     */
    @Override
    public int addNews(News news) {
        return newMapper.insertNews(news);
    }

    /**
     * 查询所有资讯
     * @return
     */
    @Override
    public List<News> selectAllNews() {
        return newMapper.selectAllNews();
    }

    /**
     * 按id查询资讯
     * @param id
     * @return
     */
    @Override
    public News selectNewsById(int id) {
        return newMapper.selectNewsById(id);
    }

}
