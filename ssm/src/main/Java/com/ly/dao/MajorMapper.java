package com.ly.dao;

import com.ly.bean.Major;

import java.util.List;

public interface MajorMapper {
    int deleteByPrimaryKey(Integer majorid);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(Integer majorid);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);

    //查询所有专业
    List getallmajor(int dapartid);
}