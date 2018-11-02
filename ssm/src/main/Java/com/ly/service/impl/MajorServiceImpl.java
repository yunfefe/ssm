package com.ly.service.impl;

import com.ly.bean.Major;
import com.ly.dao.MajorMapper;
import com.ly.service.MajorService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MajorServiceImpl implements MajorService {


    @Autowired
    private MajorMapper majorMapper;

    @Override
    public int deleteByPrimaryKey(Integer majorid) {
        return 0;
    }

    @Override
    public int insert(Major record) {
        return 0;
    }

    @Override
    public int insertSelective(Major record) {
        return 0;
    }

    @Override
    public Major selectByPrimaryKey(Integer majorid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Major record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Major record) {
        return 0;
    }

    // 根据学院id查询学院的所有专业
    @Override
    public List getallmajor(int departid) {
        List list = majorMapper.getallmajor(departid);
        return list;
    }
}
