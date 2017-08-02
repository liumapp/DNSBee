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

    /**
     * del all dns records within specify userNumber : delete_zones_ip_LM0000000
     * del specify records : delete_zones_ip_LM0000000:4.5.6.7_gmail.liumapp.com
     */
    private static final String DELETE_ZONES_IP = "delete_zones_ip_";

    //add_zones_ip_LM28937498275:127.0.0.1_test.liumapp.com
    private static final String ADD_ZONES_IP = "add_zones_ip_";

    //update_zones_ip_LM09000:4.5.6.8 gmail.liumapp.com
    private static final String UPDATE_ZONES_IP = "update_zones_ip_";

    //select_zones_ip_gmail.liumapp.com
    private static final String SELECT_ZONES_IP = "select_zones_ip_";

    private Queen queen;

    private Logger logger = Logger.getLogger(getClass());

    public static String getDeleteZonesIp() {
        return DELETE_ZONES_IP;
    }

    public static String getAddZonesIp() {
        return ADD_ZONES_IP;
    }

    public static String getUpdateZonesIp() {
        return UPDATE_ZONES_IP;
    }

    public static String getSelectZonesIp() {
        return SELECT_ZONES_IP;
    }

    public void selectIpByDomain (String domain)  {
        queen.say(SELECT_ZONES_IP + domain);
    }

    public void deleteAllByUserNumber(String userNumber) {
        queen.say(DELETE_ZONES_IP + userNumber);
    }

    public void updateByUserNumber (String userNumber , String line) {

        if (line.startsWith("#")) return;
        if (line.contains(":")) {
            line = StringUtils.substringAfterLast(line , ":");
        }
        line = StringUtils.trim(line);
        line = line.replaceAll("\\s+#[^\\s]+", "");
        line = line.replaceAll("\\s+", "_");
        queen.say(UPDATE_ZONES_IP + userNumber + ":" + line);

    }

    public void addByUserNumber(String userNumber, String line) {
        if (line.startsWith("#")) {
            return;
        }
        if (line.contains(":")) {
            line = StringUtils.substringAfterLast(line, ":");
        }
        line = StringUtils.trim(line);
        line = line.replaceAll("\\s+#[^\\s]+", "");
        line = line.replaceAll("\\s+", "_");
        queen.say(ADD_ZONES_IP + userNumber + ":" + line);
    }

    public void execute (String command) {
        queen.say(command);
    }

    public String getReturnInfo () throws IOException {
        if (isConnected()) {
            return queen.hear();
        } else {
            throw new IOException("lost connection to queen");
        }

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
