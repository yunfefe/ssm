package com.ly.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.bean.Role;
import com.ly.dao.RoleMapper;
import com.ly.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public int deleteByPrimaryKey(Integer roleid) {
        return 0;
    }

    @Override
    public int insert(Role record) {


        return 0;
    }

    @Override
    public int insertSelective(Role record) {
        return 0;
    }

    @Override
    public Role selectByPrimaryKey(Integer roleid) {
        return roleMapper.selectByPrimaryKey(roleid);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return 0;


    }

    @Override
    public PageInfo getRoleList(int index,int size) {
        PageHelper.startPage(index,size);//要在第一行
        List roleList = roleMapper.getRoleList();
        PageInfo pageInfo = new PageInfo(roleList);
        return pageInfo;
    }

    @Override
    public int addrole(Role r, int[] menu) {
        //添加角色表
        int k = roleMapper.insert(r);
        //添加中间表
        Map map = new HashMap();
        map.put("roleid",r.getRoleid());
        map.put("ids",menu);
        int k2 = roleMapper.insertMiddle(map);
        return k2;
    }

    @Override
    @Transactional
    public int update(Role role, int[] menu) {
        int k = 0;
        //修改角色表
        try {
            roleMapper.updateByPrimaryKey(role);
            //修改中间表（先删后增）
            roleMapper.deletebyroleid(role.getRoleid());
            Map map = new HashMap();
            map.put("roleid",role.getRoleid());
            map.put("ids",menu);
            roleMapper.insertMiddle(map);
            k = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }

    @Override
    public int delete(int rid) {
        //查询用户
        int count = roleMapper.selectuserbyrid(rid);
        if (count>0){
            return 0;
        }
        //先删除中间表
         roleMapper.deletebyroleid(rid);
        //再删角色表
        roleMapper.deleteByPrimaryKey(rid);
        return 1;
    }
}