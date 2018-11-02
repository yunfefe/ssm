package com.ly.dao;

import com.ly.bean.Middle;

public interface MiddleMapper {
    int deleteByPrimaryKey(Integer middleid);

    int insert(Middle record);

    int insertSelective(Middle record);

    Middle selectByPrimaryKey(Integer middleid);

    int updateByPrimaryKeySelective(Middle record);

    int updateByPrimaryKey(Middle record);
}