package com.endava.spring.dao.impl;

import com.endava.spring.dao.UserDao;
import com.endava.spring.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbogdanschi on 25/04/2017.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<User> listUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public User findByUserName(String username) {
        return (User)sessionFactory.getCurrentSession()
                .createQuery("from User where username = :username")
                .setString("username", username)
                .uniqueResult();


    }

    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }


    @Override
    public User findByEmail(String email) {
        return (User)sessionFactory.getCurrentSession()
                .createQuery("from User where email = :email")
                .setString("email", email)
                .uniqueResult();
    }
}
