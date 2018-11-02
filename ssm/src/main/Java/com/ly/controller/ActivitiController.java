package com.ly.controller;

import com.ly.bean.Leavebill;
import com.ly.bean.UserTb;
import com.ly.service.ActivitService;
import com.ly.service.LeavebillService;
import com.sun.deploy.net.HttpResponse;
import org.activiti.engine.RepositoryService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.events.Comment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class ActivitiController {

    @Autowired
    private ActivitService activitService;

    @Autowired
    private LeavebillService leavebillService;


    //查询部署信息
    @RequestMapping("/bushu/getdeploys")
    public String getdeploys(ModelMap map){
        List list1 = activitService.chaxun1();
        List list2 = activitService.chaxun2();
        map.put("deplist",list1);
        map.put("prolist",list2);
        return "/bushu/list";
    }

    //添加流程部署
    @RequestMapping("/bushu/adddeploy")
    public String add(MultipartFile depfile,String name){
        try {
            activitService.add(depfile.getInputStream(),name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/bushu/getdeploys";
    }

    //查看流程图
    @RequestMapping("/bushu/lookimage")
    public void lookimage(String depid, String imagename, HttpServletResponse response){
        try {
            InputStream inputStream = activitService.lookimage(depid, imagename);
            FileUtils.copyInputStreamToFile(inputStream,new File("d://"+imagename));
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("<script>alert('流程图的位置在d盘下，文件名为"+imagename+"');location.href='/bushu/getdeploys'</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //删除部署信息
    @RequestMapping("/bushu/delete")
    public void delete(String depid ,HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        try {
            activitService.delete(depid);

            response.getWriter().print("<script>location.href='/bushu/getdeploys'</script>");
        } catch (IOException e) {
            try {
                response.getWriter().print("<script>alert('流程正在使用，不能删除');location.href='/bushu/getdeploys'</script>");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    //提交请假申请
    @RequestMapping("/qingjia/tijiao")
    public String tijiao(int id,HttpSession session){
        UserTb u1 = (UserTb) session.getAttribute("u1");
        activitService.qingjia(id,u1.getUserName());

        return "redirect:/qingjia/getqingjia";
    }

    //查询待审批的任务
    @RequestMapping("/renwu/gettasklist")
    public String chaxuntask(HttpSession session,ModelMap map){
        UserTb u1 = (UserTb) session.getAttribute("u1");
        String userName = u1.getUserName();
        List tasklist = activitService.gettasklist(userName);
        map.put("tasks",tasklist);
        return "/renwu/list";
    }

    //办理任务,得到form_key
    @RequestMapping("/renwu/banli")
    public String chakan(String taskid){
        String formkey = activitService.getformkey(taskid);
        return "redirect:/"+formkey+"?taskid="+taskid;
    }

    //处理form_key
    @RequestMapping("/shenhe")
    public String banli(String taskid,ModelMap map){
        Leavebill leavebill = activitService.banli(taskid);
        //得到流程图中连线的内容
        List getnames = activitService.getnames(taskid);
        map.put("names",getnames);
        map.put("leavebill",leavebill);
        map.put("taskid",taskid);
        List<Comment> list = activitService.getcomments(taskid);
        map.put("commentlist",list);
        return "/renwu/banli";
    }

    //审批任务
    @RequestMapping("/renwu/shenpi")
    public String shenpi(String taskid,int leaveid,String pizhu,String result,HttpSession session){
        UserTb u1 = (UserTb) session.getAttribute("u1");
        activitService.shenpi(leaveid,taskid,result,pizhu,u1.getUserName());

        return "redirect:/renwu/gettasklist";
    }





}
