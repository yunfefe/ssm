package com.ly.bean;

import java.util.List;

public class Role {
    private Integer roleid;

    private String rolename;

    private Integer rolestate;

    //角色对应多个用户
    private List users;

    //角色对应多个菜单
    private List<Menu> menus;

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List getUsers() {
        return users;
    }

    public void setUsers(List users) {
        this.users = users;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Integer getRolestate() {
        return rolestate;
    }

    public void setRolestate(Integer rolestate) {
        this.rolestate = rolestate;
    }
}