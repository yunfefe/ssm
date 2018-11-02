package com.ly.service;

import com.github.pagehelper.PageInfo;
import com.ly.bean.Classes;

import java.util.List;

public interface ClassService {
    //查询班级信息
    public PageInfo getall(String classname,String classnum,int pageindex,int size,int[] ids,String classstate);

    int deleteByPrimaryKey(Integer classid);

    //新增班级
    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer classid);

    //改变班级状态
    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);


}
