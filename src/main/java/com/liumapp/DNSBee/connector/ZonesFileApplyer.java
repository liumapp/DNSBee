package com.liumapp.DNSBee.connector;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Service
public class ZonesFileApplyer {

    @Autowired
    private DnsbroodConnector dnsbroodConnector;

    private Logger logger = Logger.getLogger(getClass());

    public void apply(String ip, String text) {

        BufferedReader bufferedReader = new BufferedReader(new StringReader(
                text));
        String line = null;
        try {
//            dnsbroodConnector.deleteAllByIp(ip);
            while ((line = bufferedReader.readLine()) != null) {
                dnsbroodConnector.addByIp(ip, line);
            }
        } catch (IOException e) {
            logger.info("wtf!??", e);
        }
    }
}
