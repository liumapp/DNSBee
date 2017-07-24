package com.liumapp.DNSBee.web;

import com.liumapp.DNSBee.model.UserPassport;
import com.liumapp.DNSBee.util.RequestThreadUtils;
import com.liumapp.DNSBee.util.UserPassportUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liumapp on 7/24/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Controller
@RequestMapping("logout")
public class LogoutController extends MultiActionController {


    @ResponseBody
    @RequestMapping(value = "")
    public void doLogout(HttpServletRequest request , HttpServletResponse response ) {
        UserPassport userPassport = RequestThreadUtils.getUserPassport();
        UserPassportUtil.removeUserPassportCookie(response , userPassport);
        try {
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
