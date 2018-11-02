package com.ly.service.impl;

import com.ly.bean.Menu;
import com.ly.bean.Role;
import com.ly.bean.UserTb;
import com.ly.dao.MenuMapper;
import com.ly.dao.UserTbMapper;
import com.ly.service.UserTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserTbServiceImpl implements UserTbService {

    @Autowired
    private UserTbMapper userTbMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return 0;
    }

    @Override
    public int insert(UserTb record) {
        return 0;
    }

    @Override
    public int insertSelective(UserTb record) {
        return 0;
    }

    @Override
    public UserTb selectByPrimaryKey(Integer userId) {
        return userTbMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(UserTb record) {
        return userTbMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserTb record) {
        return 0;
    }

    //登录
    @Override
    public UserTb login(UserTb userTb) {
        UserTb userTb1 = userTbMapper.login(userTb.getUserName());
        if (userTb1!=null && userTb.getUserPs().equals(userTb1.getUserPs())){
            if (userTb1.getRole().getRolestate()==1){
                Role role = userTb1.getRole();
                //根据角色id查询菜单集合
                List<Menu> menuList = menuMapper.findbyroleid(role.getRoleid());
                List<Menu> fenjimenu = new ArrayList<>();
                for (Menu menu1 : menuList) {
                    if (menu1.getUpmenuid()==-1){
                        List<Menu> newlist = new ArrayList<>();//保存二级菜单
                        for (Menu menu2 : menuList) {
                            if (menu2.getUpmenuid()==menu1.getMenuid()){
                                newlist.add(menu2);
                            }
                        }
                        menu1.setSeconds(newlist);
                        fenjimenu.add(menu1);
                    }
                }
                //将菜单赋给角色
                role.setMenus(fenjimenu);
                //将角色赋给用户
                userTb1.setRole(role);
                //修改登录次数
                userTb1.setLogincount(userTb1.getLogincount()+1);
                userTbMapper.updateByPrimaryKeySelective(userTb1);
                //查询管理人
                UserTb manager = userTbMapper.selectByPrimaryKey(userTb1.getManagerid());
                userTb1.setManager(manager);
                return userTb1;
            }else {
                return null;
            }
        }
        return null;
    }

    //查询rolename
    @Override
    public List getrolename(int did, int mid, String rolename) {
        Map map = new HashMap();
        map.put("did",did);
        map.put("mid",mid);
        map.put("rolename",rolename);
        List list = userTbMapper.getrolename(map);
        return list;
    }
}
