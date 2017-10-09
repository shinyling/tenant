package com.shiny.tenant.service.impl;

import com.shiny.tenant.bean.User;
import com.shiny.tenant.dao.UserDao;
import com.shiny.tenant.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User query() {
        return userDao.selectByNameAndPwd("shiny","123456");
    }

}
