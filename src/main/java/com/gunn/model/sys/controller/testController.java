package com.gunn.model.sys.controller;

import com.gunn.model.sys.entity.User;
import com.gunn.model.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class testController {

    @Autowired
    private UserService userService;

    @RequestMapping("insert")
    public String testInsert(){
        User user = new User();
        user.setIsAdmin("1");
        user.setUserId("1");
        user.setUserName("admin");
        user.setPassword("123456");
        userService.insertUser(user);
        return "成功";
    }
}
