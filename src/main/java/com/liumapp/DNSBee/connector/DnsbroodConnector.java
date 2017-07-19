package com.liumapp.DNSBee.connector;

import com.liumapp.DNSQueen.queen.Queen;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Service
public class DnsbroodConnector implements InitializingBean {

    //delete_zones_ip_192.168.0.1
    private static final String DELETE_ZONES_IP = "delete_zones_ip_";
    //add_zones_ip_192.168.0.1:127.0.0.1_*.dianping.com
    private static final String ADD_ZONES_IP = "add_zones_ip_";

    private Queen queen;

    private Logger logger = Logger.getLogger(getClass());

    public void deleteAllByIp(String ip) {
        queen.say(DELETE_ZONES_IP + ip);
    }

    public void addByIp(String ip, String line) {
        if (line.startsWith("#")) {
            return;
        }
        if (line.contains(":")) {
            line = StringUtils.substringAfterLast(line, ":");
        }
        line = StringUtils.trim(line);
        line = line.replaceAll("\\s+#[^\\s]+", "");
        line = line.replaceAll("\\s+", "_");
        queen.say(ADD_ZONES_IP + ip + ":" + line);
    }

    public boolean isConnected() {
        return queen.isConnected();
    }

    public void setConnected(boolean connected) {
        queen.setConnected(connected);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        queen = new Queen();
        try {
            queen.connect();
        } catch (IOException e) {
            logger.warn("connnect error", e);
        }
    }

}
