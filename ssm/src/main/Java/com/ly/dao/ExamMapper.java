package com.ly.dao;

import com.ly.bean.Exam;

public interface ExamMapper {
    int insert(Exam record);

    int insertSelective(Exam record);
}