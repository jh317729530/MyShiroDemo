package com.gunn.model.sys.service;

import com.gunn.model.sys.dao.UserMapper;
import com.gunn.model.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int insertUser(User user){
        return userMapper.insertSelective(user);
    }
}
