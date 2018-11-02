package com.ly.service;

import com.github.pagehelper.PageInfo;
import com.ly.bean.Menu;

import java.util.List;

public interface MenuService {
    int deleteByPrimaryKey(Integer menuid);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuid);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    //根据角色id查找菜单集合
    List<Menu> findbyroleid(int roleid);

    //查询所有菜单
    List<Menu> getMenuList();

    PageInfo getall(int index,int size,int upmenuid,int[] menuids);
}