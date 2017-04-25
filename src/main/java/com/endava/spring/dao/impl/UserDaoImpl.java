package com.endava.spring.dao.impl;

import com.endava.spring.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sbogdanschi on 25/04/2017.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao{

    @Autowired
    private DataSource dataSource;

    public boolean isValidUser(String username, String password) throws SQLException {
        System.out.println("In UserDaoImpl.isValidUser");
        String sqlQuery = "Select count(1) from users where username = ? and password = ?";
        PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(sqlQuery);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println(username + "---------" + password + "-----------------");
        return resultSet.next() && (resultSet.getInt(1) > 0);
    }
}
