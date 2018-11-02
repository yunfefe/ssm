package com.ly.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.bean.Classes;
import com.ly.dao.ClassesMapper;
import com.ly.dao.DepartmentMapper;
import com.ly.dao.MajorMapper;
import com.ly.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassServiceImpl implements ClassService {

   @Autowired
   private ClassesMapper classesMapper;

    @Override
    public PageInfo getall(String classname, String classnum, int pageindex, int size,int[] ids,String classstate) {
        //封装模糊查条件
        Map map =new HashMap();
        map.put("classname",classname);
        map.put("classnum",classnum);
        map.put("ids",ids);
        map.put("classstate",classstate);
        //使用PageInfo工具实现分页，1、导架包2、配置mybatis.xml
        PageHelper.startPage(pageindex,size);
        List list = classesMapper.getall(map);
        //将插入的集合存在PageInfo分页工具中
        PageInfo pi = new PageInfo(list);
        return pi;
    }

    @Override
    public int deleteByPrimaryKey(Integer classid) {
        return 0;
    }

    @Override
    @Transactional
    public int insert(Classes record) {
        return classesMapper.insert(record);
    }

    @Override
    public int insertSelective(Classes record) {
        return 0;
    }

    @Override
    public Classes selectByPrimaryKey(Integer classid) {

        return null;
    }

    //改变班级状态
    @Override
    @Transactional
    public int updateByPrimaryKeySelective(Classes record) {
        return classesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Classes record) {
        return 0;
    }
}
