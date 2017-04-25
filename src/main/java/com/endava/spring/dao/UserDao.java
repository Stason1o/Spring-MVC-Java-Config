package com.endava.spring.dao;

import java.sql.SQLException;

/**
 * Created by sbogdanschi on 25/04/2017.
 */
public interface UserDao {
    boolean isValidUser(String username, String password) throws SQLException;
}
