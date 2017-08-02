package com.liumapp.DNSBee.web;

import com.liumapp.DNSBee.connector.DnsbroodConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liumapp on 8/1/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Controller
@RequestMapping("api")
public class ApiController extends MultiActionController {

    @Autowired
    private DnsbroodConnector dnsbroodConnector;

    @ResponseBody
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String handleAdd (HttpServletRequest request , HttpServletResponse response ,
                             @RequestParam("domain") String domain ,
                             @RequestParam("value") String value ,
                             @RequestParam("userNumber") String userNumber ) {
        String line = userNumber + ":" + value + "_" + domain;
        dnsbroodConnector.execute(dnsbroodConnector.getAddZonesIp() + line);
        try {
            return dnsbroodConnector.getReturnInfo();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String handleUpdate (HttpServletRequest request , HttpServletResponse response ,
                             @RequestParam("domain") String domain ,
                             @RequestParam("value") String value ,
                             @RequestParam("userNumber") String userNumber ) {
        String line = dnsbroodConnector.getUpdateZonesIp() + userNumber +  ":" + value + "_" + domain;
        dnsbroodConnector.execute(line);
        try {
            return dnsbroodConnector.getReturnInfo();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @ResponseBody
    @RequestMapping(value = "select",method = RequestMethod.POST)
    public String handleSelect (HttpServletRequest request , HttpServletResponse response ,
                             @RequestParam("domain") String domain) {
        String line = dnsbroodConnector.getSelectZonesIp() + domain;
        dnsbroodConnector.execute(line);
        try {
            return dnsbroodConnector.getReturnInfo();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @ResponseBody
    @RequestMapping(value = "del",method = RequestMethod.POST)
    public String handleDel (HttpServletRequest request , HttpServletResponse response ,
                             @RequestParam("domain") String domain ,
                             @RequestParam("value") String value ,
                             @RequestParam("userNumber") String userNumber ) {
        String line = dnsbroodConnector.getDeleteZonesIp() + userNumber + ":" + value + "_" + domain;
        dnsbroodConnector.execute(line);
        try {
            return dnsbroodConnector.getReturnInfo();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @ResponseBody
    @RequestMapping(value = "multyDel" , method = RequestMethod.POST)
    public String handleMultyDel (HttpServletRequest request , HttpServletResponse response ,
                                    @RequestParam("userNumber") String userNumber) {
        String line = dnsbroodConnector.getDeleteZonesIp() + userNumber;
        dnsbroodConnector.execute(line);
        try {
            return dnsbroodConnector.getReturnInfo();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @ResponseBody
    @RequestMapping(value = "testPage")
    public String handTest ()
    {
        return "this is test page";
    }

}
