package com.liumapp.DNSBee.web;

import com.liumapp.DNSBee.util.IPUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import java.io.IOException;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Controller
@RequestMapping("help")
public class HelpController extends MultiActionController {

    @RequestMapping("dns")
    public ModelAndView dnsHelp() throws IOException {
        String localIp = IPUtils.getLocalIP();
        ModelAndView modelAndView = new ModelAndView("help-dns");
        modelAndView.addObject("localIp", localIp);
        return modelAndView;
    }

}
