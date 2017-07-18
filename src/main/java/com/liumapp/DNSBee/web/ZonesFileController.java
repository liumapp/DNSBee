package com.liumapp.DNSBee.web;

import com.liumapp.DNSBee.dao.ZonesFileDAO;
import com.liumapp.DNSBee.model.ZonesFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Controller
@RequestMapping("zones")
public class ZonesFileController extends MultiActionController {

    @Resource
    private ZonesFileDAO zonesFileDAO;

    @ResponseBody
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Object show(@RequestParam("id") int id) throws IOException {
        ZonesFile zonesFile = zonesFileDAO.load(id);
        return zonesFile;
    }
}
