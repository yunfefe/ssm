package com.ly.service.impl;

import com.ly.bean.Leavebill;
import com.ly.bean.UserTb;
import com.ly.dao.LeavebillMapper;
import com.ly.service.LeavebillService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeavebillServiceImpl implements LeavebillService {


    @Autowired
    private LeavebillMapper leavebillMapper;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;




    @Override
    public List findall(int userid) {
        List list = leavebillMapper.findall(userid);
        return list;
    }

    @Override
    public int insertSelective(Leavebill record) {
        leavebillMapper.insertSelective(record);
        return 1;
    }

    @Override
    public int updatestate(int id, int state) {
        Map map=new HashMap();
        map.put("leaveid",id);
        map.put("state",state);
        return leavebillMapper.updatestate(map);
    }

    @Override
    public Leavebill find(int leaveid) {
        Leavebill leavebill = leavebillMapper.selectByPrimaryKey(leaveid);
        return leavebill;
    }
}
