package com.ly.dao;

import com.ly.bean.Score;

public interface ScoreMapper {
    int deleteByPrimaryKey(Integer middleid);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer middleid);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
}