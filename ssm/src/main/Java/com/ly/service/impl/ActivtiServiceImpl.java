package com.ly.service.impl;

import com.ly.bean.Leavebill;
import com.ly.bean.UserTb;
import com.ly.service.ActivitService;
import com.ly.service.LeavebillService;
import org.activiti.engine.*;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

@Service
public class ActivtiServiceImpl implements ActivitService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private LeavebillService leavebillService;

    @Autowired
    private HistoryService historyService;

    @Override
    public int add(InputStream inputStream, String name) {
        ZipInputStream in =new ZipInputStream(inputStream);
        repositoryService.createDeployment()
                .addZipInputStream(in).name(name).deploy();
        return 1;
    }

    @Override
    public List chaxun1() {
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        return list;
    }

    @Override
    public List chaxun2() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        return list;
    }

    @Override
    public InputStream lookimage(String depid, String imagename) {
        return repositoryService.getResourceAsStream(depid,imagename);
    }

    @Override
    public int delete(String depid) {
        repositoryService.deleteDeployment(depid);
        return 1;
    }

    @Override
    public List gettasklist(String name) {
        List<Task> list = taskService.createTaskQuery().taskAssignee(name).list();

        return list;
    }

    @Override
    public int qingjia(int id, String username) {

        leavebillService.updatestate(id,1);

        //建立流程与业务的关系
        String business="LeaveBill."+id;
        Map map2 = new HashMap();
        map2.put("busid",business);
        map2.put("username",username);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("LeaveBill", business, map2);
        //完成个人任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId()).singleResult();
        taskService.complete(task.getId());
        return 1;
    }

    @Override
    public String getformkey(String taskid) {
        TaskFormData formData = formService.getTaskFormData(taskid);
        String formKey = formData.getFormKey();
        return formKey;
    }

    @Override
    public Leavebill banli(String taskid) {
        Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
        String instanceId = task.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
               .processInstanceId(instanceId).singleResult();
        String businessKey = processInstance.getBusinessKey();
        String id = businessKey.substring(businessKey.indexOf(".")+1);
        //通过请假单的id查询请假对象
        Leavebill leavebill = leavebillService.find(Integer.parseInt(id));
        return leavebill;
    }



    @Override
    public List getnames(String taskid) {
        //根据任务id查询任务对象
        Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
        //得到流程实例定义id
        String definitionId = task.getProcessDefinitionId();
        //根据流程实例id查询流程图
        ProcessDefinitionEntity entity = (ProcessDefinitionEntity) repositoryService
                .getProcessDefinition(definitionId);
        //根据流程实例id查询流程实例对象
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId()).singleResult();
        //通过流程实例对象查询当前活动id
        String activityId = processInstance.getActivityId();
        //根据当前活动id找到在流程图中位置
        ActivityImpl activity = entity.findActivity(activityId);
        //得出活动元素的线集合
        List<PvmTransition> transitions = activity.getOutgoingTransitions();
        List names = new ArrayList();
        if (transitions.size()>0){
            for (PvmTransition transition : transitions) {
                String name = (String) transition.getProperty("name");
                names.add(name);
            }
        }
        return names;


    }

    @Override
    public void shenpi(int leaveid, String taskid, String result, String pizhu, String username) {
        Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
       //添加备注信息
        Authentication.setAuthenticatedUserId(username);
        taskService.addComment(taskid,task.getProcessInstanceId(),pizhu);
        //设置流程变量的值
        Map map = new HashMap();
        map.put("rs",result);

        if (result.equals("驳回")){
            leavebillService.updatestate(leaveid,3);
        }
        //完成个人任务
        taskService.complete(taskid,map);
        //判断任务是否完成
        ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId()).singleResult();
        if (instance==null){
            leavebillService.updatestate(leaveid,2);
        }
    }

    @Override
    public List getcomments(String taskid) {
        List alllist = new ArrayList();
        Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
        String instanceId = task.getProcessInstanceId();
        List<HistoricTaskInstance> list = historyService
                .createHistoricTaskInstanceQuery().processInstanceId(instanceId)
                .list();
        if (list.size()>0){
            for (HistoricTaskInstance historicTaskInstance : list) {
                String id = historicTaskInstance.getId();
                List<Comment> taskComments = taskService.getTaskComments(id);
                alllist.addAll(taskComments);
            }
        }
        return alllist;
    }
}
