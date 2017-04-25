package com.endava.spring.service.impl;

import com.endava.spring.dao.UserDao;
import com.endava.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Created by sbogdanschi on 25/04/2017.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public boolean isValidUser(String username, String password) throws SQLException {
        System.out.println("in UserServiceImpl.isValidUser");
        return userDao.isValidUser(username, password);
    }
}
