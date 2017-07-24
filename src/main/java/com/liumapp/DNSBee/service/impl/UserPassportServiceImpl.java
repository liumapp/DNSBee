package com.liumapp.DNSBee.service.impl;

import com.liumapp.DNSBee.constant.LoginConstant;
import com.liumapp.DNSBee.dao.UserPassportDAO;
import com.liumapp.DNSBee.exception.LoginException;
import com.liumapp.DNSBee.exception.RegisterException;
import com.liumapp.DNSBee.model.UserPassport;
import com.liumapp.DNSBee.service.UserPassportSerivce;
import com.liumapp.DNSBee.util.UserPassportUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Service
public class UserPassportServiceImpl implements UserPassportSerivce {

    @Resource
    private UserPassportDAO userPassportDAO;


    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public UserPassport addUserPassport(String username, String password) throws RegisterException {
        if (userPassportDAO.getByUsername(username) != null) {
            throw new RegisterException(LoginConstant.REGISTER_ERROR_USERNAME_EXIST);
        }
        String salt = UserPassportUtil.generateSalt();
        String ticket = UserPassportUtil.generateTicket(username);
        String userNumber = UserPassportUtil.generateUserNumber();
        while (userPassportDAO.getByTicket(ticket) != null) {
            ticket = UserPassportUtil.generateTicket(username);
        }
        String passwordSalt = UserPassportUtil.salt(password, salt);
        UserPassport userPassport = new UserPassport(userNumber , username, passwordSalt, salt, ticket);
        try {
            userPassportDAO.insert(userPassport);
        } catch (Exception e) {
            throw new RegisterException(LoginConstant.REGISTER_ERROR);
        }
        UserPassport byUsername = userPassportDAO.getByUsername(username);
        userPassport.setId(byUsername.getId());
        return userPassport;
    }

    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public UserPassport doLogin(String username, String password) throws LoginException {
        UserPassport userPassport = userPassportDAO.getByUsername(username);
        if (userPassport == null) {
            throw new LoginException(LoginConstant.LOGIN_ERROR_USER_NOTEXIST);
        }
        String passwordSalt = UserPassportUtil.salt(password, userPassport.getSalt());
        if (passwordSalt.equals(userPassport.getPasswordSalt())) {
//            //登录时重新生成票
//            final String generateTicket = UserPassportUtil.generateTicket(userPassport.getUsername());
//            userPassport.setTicket(generateTicket);
//            userPassportDAO.update(userPassport);
            return userPassport;
        } else {
            throw new LoginException(LoginConstant.LOGIN_ERROR_PASSWORD_INCORRECT);
        }
    }

    @Override
    public UserPassport getUserPassport(HttpServletRequest request) {
        final String ticketFromCookie = UserPassportUtil.getTicketFromCookie(request);
        if (ticketFromCookie == null) {
            return null;
        }
        return userPassportDAO.getByTicket(ticketFromCookie);
    }

}
