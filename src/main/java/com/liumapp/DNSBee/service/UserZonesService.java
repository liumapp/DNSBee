package com.liumapp.DNSBee.service;

import com.liumapp.DNSBee.model.UserPassport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public interface UserZonesService {

    public String getZones(UserPassport userPassport);

    public void updateZones(UserPassport userPassport,String zones);

    /**
     * merge zones when not login to userZones
     * @param request
     * @param response
     * @param userPassport
     */
    public void mergeUserZones(HttpServletRequest request, HttpServletResponse response, UserPassport userPassport);

}
