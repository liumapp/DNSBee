package com.liumapp.DNSBee.service;

import com.liumapp.DNSBee.exception.LoginException;
import com.liumapp.DNSBee.exception.RegisterException;
import com.liumapp.DNSBee.model.UserPassport;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public interface UserPassportSerivce {

    public UserPassport addUserPassport(String username, String password) throws RegisterException;

    public UserPassport doLogin(String username, String password) throws LoginException;

    public UserPassport getUserPassport(HttpServletRequest request);

}
