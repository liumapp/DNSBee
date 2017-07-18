package com.liumapp.DNSBee.service;

import com.liumapp.DNSBee.model.UserPassport;
import com.liumapp.DNSBee.model.ZonesFile;

import java.util.List;
import java.util.Map;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public interface ZonesFileService {

    public Map<String,List<ZonesFile>> getZonesFileList(UserPassport userPassport);

}
