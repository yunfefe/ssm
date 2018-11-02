package com.ly.controller;

import com.github.pagehelper.PageInfo;
import com.ly.bean.Menu;
import com.ly.bean.Role;
import com.ly.service.MenuService;
import com.ly.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    //查询所有角色
    @RequestMapping("/power/role/getrolelist")
    public String list(@RequestParam(defaultValue = "1") int index,
                       @RequestParam(defaultValue = "5") int size, ModelMap map){
        PageInfo pageInfo = roleService.getRoleList(index, size);
        map.put("pi",pageInfo);
        return "power/role/list";
    }

    //查询所有菜单
    @RequestMapping("/power/role/getmenulist")
    public String getmenulist(ModelMap map){
        List<Menu> newlist = menuService.getMenuList();
        map.put("menulist",newlist);
        return "/power/role/add";
    }

    //新增角色
    @RequestMapping("/power/role/addrole")
    public String addrole(Role r,int[] menu){
        System.out.println(menu);
        int k = roleService.addrole(r, menu);
        return "redirect:/power/role/getrolelist";
    }
    //查询指定角色的详情信息
    @RequestMapping("/power/role/getall")
    public String getall(int rid,ModelMap map){
        Role role = roleService.selectByPrimaryKey(rid);
        //查询角色所有菜单
        List<Menu> menuList = menuService.getMenuList();
        map.put("role",role);
        map.put("menu",menuList);
        return "/power/role/info";
    }

    //修改角色名和对应菜单
    @RequestMapping("/power/role/update")
    public String update(Role role,int[] menu){
        roleService.update(role,menu);
        return "redirect:/power/role/getrolelist";
    }
    //删除角色
    @RequestMapping("/power/role/deleterole")
    public void delete(int rid, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            int i = roleService.delete(rid);
            if (i>0){
                writer.print("<script>alert('删除成功');location.href='/power/role/getrolelist';</script>");
            }else {
                writer.print("<script>alert('删除失败，请先删除该角色下的用户');location.href='/power/role/getrolelist';</script>");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
