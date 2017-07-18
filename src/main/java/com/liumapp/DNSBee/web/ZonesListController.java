package com.liumapp.DNSBee.web;

import com.liumapp.DNSBee.model.UserPassport;
import com.liumapp.DNSBee.model.ZonesFile;
import com.liumapp.DNSBee.service.UserPassportSerivce;
import com.liumapp.DNSBee.service.ZonesFileService;
import com.liumapp.DNSBee.util.RequestThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Controller
@RequestMapping("zones")
public class ZonesListController extends MultiActionController {

    @Autowired
    private ZonesFileService zonesFileService;

    @Autowired
    private UserPassportSerivce userPassportSerivce;

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Object show(HttpServletRequest httpServletRequest) throws IOException {
        UserPassport userPassport = RequestThreadUtils.getUserPassport();
        Map<String, List<ZonesFile>> zonesFileList = zonesFileService.getZonesFileList(userPassport);
        return zonesFileList;
    }
}
