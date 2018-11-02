package com.ly.service;

import com.ly.bean.Leavebill;
import org.activiti.engine.task.Task;

import java.io.InputStream;
import java.util.List;

public interface ActivitService {
    //添加流程部署
    int add(InputStream inputStream,String name);
    //查询流程部署
    List chaxun1();
    //查询流程定义
    List chaxun2();
    //查看流程图
    InputStream lookimage(String depid,String imagename);
    //删除部署信息
    int delete(String depid);
    //查询所有任务
    List gettasklist(String name);
    int qingjia(int leaveid,String username);
    //查询form_key
    String getformkey(String taskid);
    //查询business_key
    Leavebill banli(String taskid);
    //得到流程图中连线的内容
    List getnames(String taskid);
    //审批任务
    void shenpi(int leaveid,String taskid,String result,String pizhu,String username);
    //查询历史批注信息
    List getcomments(String taskid);
}
