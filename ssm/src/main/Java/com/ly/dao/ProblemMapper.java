package com.ly.dao;

import com.ly.bean.Problem;

public interface ProblemMapper {
    int insert(Problem record);

    int insertSelective(Problem record);
}