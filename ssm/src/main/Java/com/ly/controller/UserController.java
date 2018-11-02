package com.ly.controller;

import cn.com.webxml.MobileCodeWSSoap;
import com.ly.bean.UserTb;
import com.ly.service.UserTbService;
import com.ly.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Controller
@SessionAttributes({"u1","logintime"})
public class UserController {

    @Autowired
    private UserTbService userTbService;

    @Autowired
    private MobileCodeWSSoap mobileCodeWSSoap;

    //查询phone
    @RequestMapping("/getphone")
    public void getphone(String phone,HttpServletResponse response){
        String mobileCodeInfo = mobileCodeWSSoap.getMobileCodeInfo(phone, null);
        int index = mobileCodeInfo.lastIndexOf(" ");
        String s = mobileCodeInfo.substring(index + 1);
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.print(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //登录
    @RequestMapping("/login")
    public void login(UserTb userTb, String DropExpiration, HttpServletResponse response, ModelMap map,String yanzheng){
        String yz = ImageUtil.getCode();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter   writer = response.getWriter();
            if (yz.equalsIgnoreCase(yanzheng)) {
                UserTb userTb1 = userTbService.login(userTb);

                    if (userTb1 == null) {
                        writer.print("<script>alert('登录失败');location.href='login.jsp'</script>");
                    } else {
                        map.put("u1", userTb1);
                        Cookie cookie = new Cookie("uname", userTb1.getUserName());
                        if ("Day".equals(DropExpiration)) {
                            cookie.setMaxAge(24 * 60 * 60);
                        }
                        if ("Year".equals(DropExpiration)) {
                            cookie.setMaxAge(24 * 60 * 60 * 30 * 12);
                        }
                        if ("Month".equals(DropExpiration)) {
                            cookie.setMaxAge(24 * 60 * 60 * 30);
                        }
                        response.addCookie(cookie);
                        //登录时间
                        Date date = new Date();
                        map.put("logintime", date);
                        writer.print("<script>alert('登录成功');location.href='index.jsp'</script>");
                    }
            }else {
                writer.print("<script>alert('验证码错误');location.href='login.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //修改用户
    @RequestMapping("/user/update")
    public void update(UserTb userTb,HttpServletResponse response,ModelMap map){
        int i = userTbService.updateByPrimaryKeySelective(userTb);
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            if(i>0){
                UserTb userTb1 = userTbService.selectByPrimaryKey(userTb.getUserId());
                map.put("u1",userTb1);
                writer.print("<script>alert('修改成功');top.location.href='/index.jsp'</script>");
            }else {
                writer.print("<script>alert('修改失败');top.location.href=MyUser.jsp''</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //验证原密码
    @RequestMapping("/user/updatepassword")
    public void updatepassword(String upass,ModelMap map,HttpServletResponse response){
        UserTb u1 = (UserTb) map.get("u1");
        //判断传入的密码与后台密码是否一致
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            if (upass.equals(u1.getUserPs())){
                writer.print(true);
            }else {
                writer.print(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //修改密码
    @RequestMapping("/user/updatepassword2")
    public void aaa(UserTb userTb, HttpServletResponse response, SessionStatus status){
        int i = userTbService.updateByPrimaryKeySelective(userTb);
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            if(i>0){
                //修改成功后，需将session清除
                status.setComplete();
                writer.print("<script>alert('修改密码成功');top.location.href='/login.jsp'</script>");
            }else {
                writer.print("<script>alert('修改密码失败');location.href=password.jsp''</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
