package com.endava.spring.service;

/**
 * Created by sbogdanschi on 3/05/2017.
 */
public interface SecurityService {

    String findLoggedInUser();

    void autoLogin(String username, String password);
}
