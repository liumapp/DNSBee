package com.liumapp.DNSBee.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by liumapp on 7/18/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext-*.xml"})
public class ZonesDaoTest {

    @Resource
    private ZonesFileDAO zonesFileDAO;

    @Test
    public void test(){
        System.out.println(zonesFileDAO.findPublic());
        System.out.println(zonesFileDAO.load(1));
    }

}
