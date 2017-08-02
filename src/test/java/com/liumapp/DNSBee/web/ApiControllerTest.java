package com.liumapp.DNSBee.web;

import com.liumapp.DNSBee.util.HttpClientUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liumapp on 8/1/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"})
public class ApiControllerTest {

    private String userNumber = "LMtest";

    @Test
    public void testAdd () {

        String url = "http://localhost:9090/api/add";

        HttpClientUtil httpClientUtil = new HttpClientUtil();

        Map params = new HashMap();
        params.put("userNumber" ,userNumber);
        params.put("domain" , "junitest.liumapp.com");
        params.put("value" , "11.22.33.44");

        System.out.println(httpClientUtil.sendPost(url , params));

    }

}
