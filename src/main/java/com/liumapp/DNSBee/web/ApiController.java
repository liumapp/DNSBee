package com.liumapp.DNSBee.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liumapp on 8/1/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Controller
@RequestMapping("api")
public class ApiController extends MultiActionController {

    @ResponseBody
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String handleAdd (HttpServletRequest request , HttpServletResponse response ,
                             @RequestParam("domain") String domain ,
                             @RequestParam("value") String value ,
                             @RequestParam("userNumber") String userNumber ) {
        String line = "";


        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String handleUpdate (HttpServletRequest request , HttpServletResponse response ,
                             @RequestParam("domain") String domain ,
                             @RequestParam("value") String value ,
                             @RequestParam("userNumber") String userNumber ) {
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "select",method = RequestMethod.POST)
    public String handleSelect (HttpServletRequest request , HttpServletResponse response ,
                             @RequestParam("domain") String domain ,
                             @RequestParam("value") String value ,
                             @RequestParam("userNumber") String userNumber ) {
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "del",method = RequestMethod.POST)
    public String handleDel (HttpServletRequest request , HttpServletResponse response ,
                             @RequestParam("domain") String domain ,
                             @RequestParam("value") String value ,
                             @RequestParam("userNumber") String userNumber ) {
        return "success";
    }

}
