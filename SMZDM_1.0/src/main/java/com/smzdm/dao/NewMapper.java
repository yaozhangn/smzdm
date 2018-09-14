package com.smzdm.dao;

import com.smzdm.bean.News;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NewMapper {

    int insertNews(@Param("news") News news);

    List<News> selectAllNews();

}
