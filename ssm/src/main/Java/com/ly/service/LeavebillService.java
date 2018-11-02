package com.ly.service;

import com.ly.bean.Leavebill;
import com.ly.bean.UserTb;

import java.util.List;

public interface LeavebillService {
    List findall(int userid);

    int insertSelective(Leavebill record);

    int updatestate(int id, int state);

    Leavebill find(int leaveid);
}
