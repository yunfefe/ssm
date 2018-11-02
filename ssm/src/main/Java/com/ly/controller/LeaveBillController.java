package com.ly.controller;

import com.ly.bean.Leavebill;
import com.ly.bean.UserTb;
import com.ly.service.LeavebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LeaveBillController {

    @Autowired
    private LeavebillService leavebillService;

    //查询请假单
    @RequestMapping("/qingjia/getqingjia")
    public String getqingjia(HttpSession session, ModelMap map){
        UserTb u1 = (UserTb) session.getAttribute("u1");
        List list = leavebillService.findall(u1.getUserId());
        map.put("list",list);
        return "/qingjia/list";
    }

    //添加请假单
    @RequestMapping("/qingjia/add")
    public String add(Leavebill leavebill){
        leavebillService.insertSelective(leavebill);
        return "redirect:/qingjia/getqingjia";
    }




}
