package com.liumapp.DNSBee.service.impl;

import com.liumapp.DNSBee.dao.UserZonesDAO;
import com.liumapp.DNSBee.model.UserPassport;
import com.liumapp.DNSBee.service.UserZonesService;
import com.liumapp.DNSBee.util.CookieUtils;
import com.liumapp.DNSBee.util.UserZonesUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Service
public class UserZonesServiceImpl implements UserZonesService {

    @Resource
    private UserZonesDAO userZonesDAO;

    @Override
    public String getZones(UserPassport userPassport) {
        if (userPassport != null) {
            return userZonesDAO.getZones(userPassport.getId());
        }
        return null;
    }

    @Override
    public void updateZones(UserPassport userPassport, String zones) {
        if (userPassport != null) {
            userZonesDAO.updateZones(userPassport.getId(), zones);
        }
    }

    @Override
    public void mergeUserZones(HttpServletRequest request, HttpServletResponse response, UserPassport userPassport) {
        String userZones = getZones(userPassport);
        String cookieZones = CookieUtils.getZones(request);
        if (cookieZones != null) {
            userZones = UserZonesUtils.merge(userZones, cookieZones);
            updateZones(userPassport, userZones);
            CookieUtils.saveZones(response,cookieZones);
        }
    }
}
