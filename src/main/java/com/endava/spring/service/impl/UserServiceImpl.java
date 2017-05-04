package com.endava.spring.service.impl;

import com.endava.spring.dao.UserDao;
import com.endava.spring.model.User;
import com.endava.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sbogdanschi on 25/04/2017.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public User findByUserName(String username){
        return userDao.findByUserName(username);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
