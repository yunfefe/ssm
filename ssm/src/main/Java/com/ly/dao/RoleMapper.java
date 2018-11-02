package com.ly.dao;

import com.ly.bean.Role;
import org.apache.commons.fileupload.util.LimitedInputStream;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    //查询所有角色
    List getRoleList();

    int insertMiddle(Map map);

    //修改中间表
    int updateMiddle(Map map);
    //删除中间表
    int deletebyroleid(int roleid);

     int selectuserbyrid(int rid);



}