package com.liumapp.DNSBee.web;

import com.liumapp.DNSBee.connector.DnsbroodConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public void handleAdd (HttpServletRequest request , HttpServletResponse response ,
                             @RequestParam("domain") String domain ,
                             @RequestParam("value") String value ,
                             @RequestParam("userNumber") String userNumber ) {
        String line = userNumber + ":" + value + "_" + domain;
        dnsbroodConnector.execute(dnsbroodConnector.getAddZonesIp() + line);
        try {
            System.out.println(dnsbroodConnector.getReturnInfo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public void handleUpdate (HttpServletRequest request , HttpServletResponse response ,
                             @RequestParam("domain") String domain ,
                             @RequestParam("value") String value ,
                             @RequestParam("userNumber") String userNumber ) {
        String line = dnsbroodConnector.getUpdateZonesIp() + userNumber +  ":" + value + "_" + domain;
        dnsbroodConnector.execute(line);
        try {
            System.out.println(dnsbroodConnector.getReturnInfo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "select",method = RequestMethod.POST)
    public void handleSelect (HttpServletRequest request , HttpServletResponse response ,
                             @RequestParam("domain") String domain) {
        String line = dnsbroodConnector.getSelectZonesIp() + domain;
        dnsbroodConnector.execute(line);
        try {
            System.out.println(dnsbroodConnector.getReturnInfo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "del",method = RequestMethod.POST)
    public void handleDel (HttpServletRequest request , HttpServletResponse response ,
                             @RequestParam("domain") String domain ,
                             @RequestParam("value") String value ,
                             @RequestParam("userNumber") String userNumber ) {
        String line = dnsbroodConnector.getDeleteZonesIp() + userNumber + ":" + value + "_" + domain;
        dnsbroodConnector.execute(line);
        try {
            System.out.println(dnsbroodConnector.getReturnInfo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "multyDel" , method = RequestMethod.POST)
    public void handleMultyDel (HttpServletRequest request , HttpServletResponse response ,
                                    @RequestParam("userNumber") String userNumber) {
        String line = dnsbroodConnector.getDeleteZonesIp() + userNumber;
        dnsbroodConnector.execute(line);
        try {
            System.out.println(dnsbroodConnector.getReturnInfo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
