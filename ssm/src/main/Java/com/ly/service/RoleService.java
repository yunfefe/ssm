package com.ly.service;

import com.github.pagehelper.PageInfo;
import com.ly.bean.Role;

import java.util.List;

public interface RoleService {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    PageInfo getRoleList(int index,int size);

    int addrole(Role r,int[] ids);

    //修改角色表
     int update(Role role,int[] menu);

     //删除角色
    int delete(int rid);


}