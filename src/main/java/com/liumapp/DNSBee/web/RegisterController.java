package com.liumapp.DNSBee.web;

import com.liumapp.DNSBee.exception.RegisterException;
import com.liumapp.DNSBee.model.JsonResult;
import com.liumapp.DNSBee.model.UserPassport;
import com.liumapp.DNSBee.service.UserPassportSerivce;
import com.liumapp.DNSBee.service.UserZonesService;
import com.liumapp.DNSBee.util.UserPassportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Map;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Controller
@RequestMapping("register")
public class RegisterController extends MultiActionController {

    @Autowired
    private UserPassportSerivce userPassportSerivce;

    @Autowired
    private UserZonesService userZonesService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        ModelAndView modelAndView = new ModelAndView("register");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object doRegister(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password) {
        UserPassport userPassport = null;
        try {
            userPassport = userPassportSerivce.addUserPassport(username, password);
            UserPassportUtil.saveUserPassportCookie(response, userPassport);
            Map<String, Object> resultMap = JsonResult.success("Sign up success！").toMap();
//            userZonesService.mergeUserZones(request, response, userPassport);
            resultMap.put("token", userPassport.getTicket());
            return resultMap;
        } catch (RegisterException e) {
            return JsonResult.error(messageSource.getMessage(e.getCode(), null, Locale.CHINA));
        }
    }

}

