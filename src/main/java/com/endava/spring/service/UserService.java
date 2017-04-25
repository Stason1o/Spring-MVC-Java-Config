package com.endava.spring.service;

import java.sql.SQLException;

/**
 * Created by sbogdanschi on 25/04/2017.
 */
public interface UserService {

    boolean isValidUser(String username, String password) throws SQLException;

}
