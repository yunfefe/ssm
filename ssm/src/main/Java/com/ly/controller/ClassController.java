package com.ly.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.bean.Classes;
import com.ly.dao.MajorMapper;
import com.ly.dao.UserTbMapper;
import com.ly.service.ClassService;
import com.ly.service.DepartmentService;
import com.ly.service.MajorService;
import com.ly.service.UserTbService;
import com.ly.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class ClassController {

    @Autowired
    private ClassService classService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private MajorService majorService;

    @Autowired
    private UserTbService userTbService;


    //查询所有班级
    @RequestMapping("/Educational/class/getclasslist")
    public String aaa(String classname, String classnum,
                      @RequestParam(value = "index",defaultValue = "1") int pageindex,
                      @RequestParam(value = "size",defaultValue = "5") int size, ModelMap map){
        PageInfo pi = classService.getall(classname, classnum, pageindex, size,null,null);
        pi.setPageSize(size);
        map.put("pi",pi);
        map.put("classname",classname);
        map.put("classnum",classnum);
        map.put("size",size);
        return "/Educational/class/list";
    }

    //查询所有学院
    @RequestMapping("/Educational/class/getdeparts")
    public String getdeparts(ModelMap map){
        List list = departmentService.getalldeparts();
        map.put("departlist",list);
        return "/Educational/class/add";
    }

    //利用ajax动态查询学院的所有专业
    @RequestMapping("/Educational/class/getallmajor")
    @ResponseBody
    public List getallmajor(int did){
        List list = majorService.getallmajor(did);
        return list;
    }

     //根据学院id，专业id查询用户角色
    @RequestMapping("/Educational/class/getrolename")
    @ResponseBody
    public List getrolename(int did,int mid){
        List list = userTbService.getrolename(did, mid, "班主任");
        return list;
    }

    //新增班级
    @RequestMapping("/Educational/class/insertclass")
    public String insertclass(Classes classes){
        classes.setClassstate("未审核");
       classService.insert(classes);
        return "redirect:/Educational/class/getclasslist";
    }

    //根据班级状态改变操作状态
    @RequestMapping("/Educational/class/updateclassstate")
    public String updateclassstate(Classes classes){
        classes.setClassstate("审核中");
        classes.setAuditcount(classes.getAuditcount()+1);//提交审核后审核次数加1
        classService.updateByPrimaryKeySelective(classes);
        return "redirect:/Educational/class/getclasslist";
    }

    //导出数据
    @RequestMapping("/Educational/class/todaochu")
    public void daochu(int[] single, HttpServletResponse response) {
        PageInfo pg = classService.getall(null, null, 0, 0, single,null);
        System.out.println(Arrays.toString(single));
        List<Classes> list = pg.getList();
        ExcelUtil.headers = new String[]{"院系", "班级编号", "班级名称", "班主任老师", "人数", "班级状态"};
        ExcelUtil.createhead(6);
        ExcelUtil.createother(list);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = simpleDateFormat.format(new Date());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("d:\\class" + date + ".xls");
            ExcelUtil.export(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out2 = response.getWriter();
            out2.print("<script>alert('导出成功');location.href='/Educational/class/getclasslist'</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //班级审核-查询班级状态为审核中的班级
    @RequestMapping("/Educational/getclasses")
    public String getclasses(String classname, String classnum,
                             @RequestParam(value = "index",defaultValue = "1") int pageindex,
                             @RequestParam(value = "size",defaultValue = "5") int size, ModelMap map){
        PageInfo pi = classService.getall(classname, classnum, pageindex, size,null,"审核中");
        pi.setPageSize(size);
        map.put("pi",pi);
        map.put("classname",classname);
        map.put("classnum",classnum);
        map.put("size",size);
        return "/Educational/Auditing";
    }

    //改变审核状态
    @RequestMapping("/Educational/udpatestate")
    public String updatestate(Classes classes){
        classService.updateByPrimaryKeySelective(classes);
        return "redirect:/Educational/getclasses";
    }
}
