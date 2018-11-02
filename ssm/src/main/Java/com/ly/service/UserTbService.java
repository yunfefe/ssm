package com.ly.service;

import com.ly.bean.UserTb;

import java.util.List;

public interface UserTbService {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserTb record);

    int insertSelective(UserTb record);

    UserTb selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserTb record);

    int updateByPrimaryKey(UserTb record);

    //登录
    UserTb login(UserTb userTb);

    //根据学院，专业查询用户角色
    List getrolename(int did,int mid,String rolename);
}
