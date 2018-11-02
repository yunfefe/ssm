package com.ly.dao;

import com.ly.bean.Leavebill;

import java.util.List;
import java.util.Map;

public interface LeavebillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Leavebill record);

    int insertSelective(Leavebill record);

    Leavebill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Leavebill record);

    int updateByPrimaryKey(Leavebill record);

    List findall(int userid);

    int updatestate(Map map);


}