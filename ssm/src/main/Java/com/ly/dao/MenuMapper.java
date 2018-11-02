package com.ly.dao;

import com.ly.bean.Menu;

import java.util.List;
import java.util.Map;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer menuid);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuid);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    //根据角色id查找菜单集合
    List<Menu> findbyroleid(int roleid);

    //查询所有菜单
    List<Menu> getMenuList(Map map);

    //查询是否有子菜单的方法
    int selectmeunbyid(int menuid);

    //通过menuid删中间表
    int deleteMiddlebyid(int menuid);
}