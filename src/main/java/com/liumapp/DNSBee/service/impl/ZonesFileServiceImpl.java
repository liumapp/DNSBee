package com.liumapp.DNSBee.service.impl;

import com.liumapp.DNSBee.dao.ZonesFileDAO;
import com.liumapp.DNSBee.model.UserPassport;
import com.liumapp.DNSBee.model.ZonesFile;
import com.liumapp.DNSBee.service.ZonesFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Service
public class ZonesFileServiceImpl implements ZonesFileService{

    @Resource
    private ZonesFileDAO zonesFileDAO;

    @Override
    public Map<String, List<ZonesFile>> getZonesFileList(UserPassport userPassport) {
        Map<String, List<ZonesFile>> result = new LinkedHashMap<String, List<ZonesFile>>();
        List<ZonesFile> zonesFiles = zonesFileDAO.findPublic();
        result.put("public", zonesFiles);
        if (userPassport != null) {
            List<ZonesFile> byUser = zonesFileDAO.findByUser(userPassport.getUsername());
            result.put("personal", byUser);
        } else {
            result.put("personal", Collections.<ZonesFile>emptyList());
        }
        return result;
    }
}
