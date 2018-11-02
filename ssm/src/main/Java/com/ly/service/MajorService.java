package com.ly.service;

import com.ly.bean.Major;

import java.util.List;

public interface MajorService {
    int deleteByPrimaryKey(Integer majorid);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(Integer majorid);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);


    //根据学院id查询学院的所有专业
    public List getallmajor(int departid);
}
