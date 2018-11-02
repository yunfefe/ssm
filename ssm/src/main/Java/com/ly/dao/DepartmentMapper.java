package com.ly.dao;

import com.ly.bean.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer departid);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer departid);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    //查询所有学院
    List getalldeparts();
}