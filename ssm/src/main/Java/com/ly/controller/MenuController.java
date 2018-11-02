package com.ly.controller;

import com.github.pagehelper.PageInfo;
import com.ly.bean.Menu;
import com.ly.service.MenuService;
import com.ly.util.ExcelUtil;
import com.ly.util.MenuExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.awt.SystemColor.menu;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;
//展示所有菜单
    @RequestMapping("/power/menu/getmenu")
    public String getall(@RequestParam(defaultValue = "1") int index,
                         @RequestParam(defaultValue = "5") int size, ModelMap map){
        PageInfo pageInfo = menuService.getall(index, size,-10,null);
        map.put("pi",pageInfo);
        return "/power/menu/list";
    }
//展示所有一级菜单
    @RequestMapping("/power/menu/getnewmenu")
    public String getall2(ModelMap map){
        PageInfo pageInfo = menuService.getall(0, 0,-1,null);
        map.put("pi",pageInfo);
        return "/power/menu/add";
    }
    //添加菜单
    @RequestMapping("/power/menu/addmenu")
    public String getall3(Menu menu){
        menuService.insert(menu);
        return "redirect:/power/menu/getmenu";
    }
    //删除菜单
    @RequestMapping("/power/menu/deletemenu")
    public void delete(int menuid, HttpServletResponse response){
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            int i = menuService.deleteByPrimaryKey(menuid);
            if (i>0){
                writer.print("<script>alert('删除成功');location.href='/power/menu/getmenu'</script>");
            }else{
                writer.print("<script>alert('删除失败，请先删除此菜单的子菜单');location.href='/power/menu/getmenu'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //修改菜单1.先查
    @RequestMapping("/power/menu/updatemenu")
    public String chaxun(int upmenuid,int menuid,ModelMap map){
        PageInfo pageInfo = menuService.getall(0, 0, -1,null);
        map.put("pi",pageInfo);
        Menu menu = menuService.selectByPrimaryKey(menuid);
        map.put("menu",menu);
        return "/power/menu/edit";
    }

    //2.再改
    @RequestMapping("/power/menu/update")
    public String update(Menu menu){
        menuService.updateByPrimaryKeySelective(menu);
        return "redirect:/power/menu/getmenu";
    }
    //导出文件
    @RequestMapping("/power/menu/todaochu2")
    public void todaochu(int[] single,HttpServletResponse response){
        System.out.println(single);
        PageInfo pageInfo = menuService.getall(0, 0, -10,single);
        List<Menu> list = pageInfo.getList();
        for (Menu menu1 : list) {
            System.out.println(menu1);
        }
        MenuExcelUtil.headers=new String[]{"序号","菜单名称","URL","状态"};
        MenuExcelUtil.createhead(4);
        MenuExcelUtil.createother(list);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = simpleDateFormat.format(new Date());
        FileOutputStream out = null;
        try {
            out= new FileOutputStream("d:\\menu"+date+".xls");
            MenuExcelUtil.export(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out2 = response.getWriter();
            out2.print("<script>alert('导出成功');location.href='/power/menu/getmenu'</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
