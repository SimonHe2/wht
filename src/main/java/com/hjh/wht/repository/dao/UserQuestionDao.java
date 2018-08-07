package com.hjh.wht.repository.dao;

import com.hjh.wht.repository.model.UserQuestionPo;

public interface UserQuestionDao {
    int insert(UserQuestionPo record);

    int insertSelective(UserQuestionPo record);
}