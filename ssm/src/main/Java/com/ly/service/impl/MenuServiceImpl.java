package com.ly.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.bean.Menu;
import com.ly.dao.MenuMapper;
import com.ly.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

   @Autowired
   private MenuMapper menuMapper;
    @Override
    public int deleteByPrimaryKey(Integer menuid) {
        //查询是否有子菜单
        int count = menuMapper.selectmeunbyid(menuid);
        if (count>0){
            return 0;
        }
        //先删中间表
        menuMapper.deleteMiddlebyid(menuid);
        //再删菜单表
        menuMapper.deleteByPrimaryKey(menuid);
        return 1;
    }

    @Override
    public int insert(Menu record) {
        return menuMapper.insert(record);
    }

    @Override
    public int insertSelective(Menu record) {
        return 0;
    }

    @Override
    public Menu selectByPrimaryKey(Integer menuid) {
        return menuMapper.selectByPrimaryKey(menuid);
    }

    @Override
    public int updateByPrimaryKeySelective(Menu record) {
        return menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Menu record) {
        return 0;
    }

    //根据角色id查询对应菜单
    @Override
    public List<Menu> findbyroleid(int roleid) {
        List<Menu> menuList = menuMapper.findbyroleid(roleid);
        return menuList;
    }

    //查询菜单集合
    @Override
    public List<Menu> getMenuList() {
        //调取dao层的方法
        Map map = new HashMap();
        map.put("upmenuid",-10);
        List<Menu> menuList = menuMapper.getMenuList(map);
        //给菜单分级
        List newlist = new ArrayList();
        for (Menu menu1 : menuList) {
            if (menu1.getUpmenuid()==-1){
                List menus = new ArrayList();
                for (Menu menu2 : menuList) {
                    if (menu2.getUpmenuid()==menu1.getMenuid()){
                        menus.add(menu2);
                    }
                }
                menu1.setSeconds(menus);
                newlist.add(menu1);
            }
        }
        return newlist;
    }

    //查询所有菜单，不用分层
    public PageInfo getall(int index,int size,int upmenuid,int[] menuids){
        PageHelper.startPage(index,size);
        Map map = new HashMap();
        map.put("upmenuid",upmenuid);
        map.put("menuids",menuids);
        List<Menu> menuList = menuMapper.getMenuList(map);
        PageInfo pageInfo = new PageInfo(menuList);
        return pageInfo;

    }
}