package com.liumapp.DNSBee.web;

import com.liumapp.DNSBee.model.UserPassport;
import com.liumapp.DNSBee.service.UserZonesService;
import com.liumapp.DNSBee.util.CookieUtils;
import com.liumapp.DNSBee.util.IPUtils;
import com.liumapp.DNSBee.util.RequestThreadUtils;
import com.liumapp.DNSBee.util.UserZonesUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Controller
@RequestMapping("")
public class ZonesPickController extends MultiActionController {

    @Autowired
    private UserZonesService userZonesService;

    @Autowired
    private ZonesApplyController zonesApplyController;

    @RequestMapping("z")
    public ModelAndView dashboardZ(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false, value = "z") String zonesBase64) throws IOException {
        return dashboard(request,response,zonesBase64);
    }

    @RequestMapping("")
    public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false, value = "z") String zonesBase64) throws IOException {
        UserPassport userPassport = RequestThreadUtils.getUserPassport();
        String zones = userZonesService.getZones(userPassport);
        if (zones == null) {
            zones = CookieUtils.getZones(request);
        }
        if (StringUtils.isBlank(zones) && !StringUtils.isBlank(zonesBase64)) {
            zonesBase64 = zonesBase64.replaceAll("^\"","").replaceAll("\"$","");
            BASE64Decoder base64Decoder = new BASE64Decoder();
            zones = new String(base64Decoder.decodeBuffer(zonesBase64), "utf-8");
            CookieUtils.saveZones(response, zones);
        }
        String localIp = IPUtils.getLocalIP();
        ModelAndView modelAndView = new ModelAndView("zonespick");
        modelAndView.addObject("zones", UserZonesUtils.toJson(zones));
        modelAndView.addObject("localIp", localIp);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("pick")
    public Object pick(HttpServletRequest request, HttpServletResponse response, @RequestParam("json") String json) throws IOException {
        String text = UserZonesUtils.fromJson(json);
        UserPassport userPassport = RequestThreadUtils.getUserPassport();
        userZonesService.updateZones(userPassport, text);
        CookieUtils.saveZones(response, text);
        return zonesApplyController.save(text, request);
    }

}