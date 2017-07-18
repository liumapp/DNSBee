package com.liumapp.DNSBee.web;

import com.liumapp.DNSBee.model.UserPassport;
import com.liumapp.DNSBee.model.ZonesFile;
import com.liumapp.DNSBee.service.ZonesFileService;
import com.liumapp.DNSBee.util.RequestThreadUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Controller
@RequestMapping("share")
public class ShareController extends MultiActionController {

    @Autowired
    private ZonesFileService zonesFileService;

    @RequestMapping("")
    public ModelAndView dashboard(HttpServletRequest request) throws IOException {
        ModelAndView modelAndView = new ModelAndView("shareboard");
        UserPassport userPassport = RequestThreadUtils.getUserPassport();
        Map<String, List<ZonesFile>> zonesFileList = zonesFileService.getZonesFileList(userPassport);
        modelAndView.addAllObjects(zonesFileList);

        constructJsonData(modelAndView, zonesFileList);
        return modelAndView;
    }

    private void constructJsonData(ModelAndView modelAndView, Map<String, List<ZonesFile>> zonesFileList) throws IOException {
        Map<Integer, ZonesFile> dataMap = new LinkedHashMap<Integer, ZonesFile>();
        for (List<ZonesFile> zonesFiles : zonesFileList.values()) {
            for (ZonesFile zonesFile : zonesFiles) {
                dataMap.put(zonesFile.getId(), zonesFile);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String data = objectMapper.writeValueAsString(dataMap);
        modelAndView.addObject("data", data);
    }

}
