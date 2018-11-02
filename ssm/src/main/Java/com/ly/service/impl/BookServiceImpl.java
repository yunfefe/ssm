package com.ly.service.impl;

import com.ly.bean.Information;
import com.ly.dao.InformationMapper;
import com.ly.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {


    @Autowired
    private InformationMapper informationMapper;

    @Override
    public int insertSelective(Information record) {

        return informationMapper.insertSelective(record);
    }

    @Override
    public List getbooklist(Information information) {

        Map map = new HashMap();
        map.put("information",information);
        return informationMapper.getbooklist(map);
    }
}
