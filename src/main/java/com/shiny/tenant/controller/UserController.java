package com.shiny.tenant.controller;

import com.shiny.tenant.bean.User;
import com.shiny.tenant.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("query")
    public User query(){
        return userService.query();
    }
}
