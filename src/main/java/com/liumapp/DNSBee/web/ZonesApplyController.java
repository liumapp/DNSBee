package com.liumapp.DNSBee.web;

import com.liumapp.DNSBee.connector.DnsbroodConnector;
import com.liumapp.DNSBee.connector.ZonesFileApplyer;
import com.liumapp.DNSBee.model.JsonResult;
import com.liumapp.DNSBee.util.IPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Controller
@RequestMapping("apply")
public class ZonesApplyController extends MultiActionController {

    @Autowired
    private ZonesFileApplyer zonesFileApplyer;

    @Autowired
    private DnsbroodConnector dnsbroodConnector;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Object save(@RequestParam("text") String text, HttpServletRequest request) {
//        zonesFileApplyer.apply(IPUtils.getClientIp(request), text);
        zonesFileApplyer.apply(IPUtils.LOCAL_LOOP_ADDRESS , text);

        if (dnsbroodConnector.isConnected()) {
            return JsonResult.success("Aplly success!");
        } else {
            return JsonResult.error("Aplly failed，Blackhole is not connected！");
        }
    }

}
