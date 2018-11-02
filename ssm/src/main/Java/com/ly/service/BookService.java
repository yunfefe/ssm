package com.ly.service;

import com.ly.bean.Information;

import java.util.List;

public interface BookService {
    //增加资料
    int insertSelective(Information record);
    //查询所有
    List getbooklist(Information information);
}
