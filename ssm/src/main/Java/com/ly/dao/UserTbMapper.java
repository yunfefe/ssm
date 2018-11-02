package com.ly.dao;

import com.ly.bean.Classes;
import com.ly.bean.UserTb;

import java.util.List;
import java.util.Map;

public interface UserTbMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Classes record);

    int insertSelective(UserTb record);

    UserTb selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserTb record);

    int updateByPrimaryKey(UserTb record);

    //登录
    UserTb login(String name);

    ////根据学院，专业查询用户角色
    List getrolename(Map map);
}
