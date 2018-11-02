package com.ly.service.impl;

import com.ly.dao.DepartmentMapper;
import com.ly.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    //查询所有学院
    @Override
    public List getalldeparts() {
        List list = departmentMapper.getalldeparts();
        return list;
    }
}
