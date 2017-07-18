package com.liumapp.DNSBee.dao;

import com.liumapp.DNSBee.model.UserPassport;
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
public class UserPassportDaoTest {
    @Resource
    private UserPassportDAO userPassportDAO;

    @Test
    public void test() {
        UserPassport userPassport = new UserPassport("admin", "admin123", "123", "12345161");
        try {
            int insert = userPassportDAO.insert(userPassport);
            System.out.println(insert);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserPassport byTicket = userPassportDAO.getByTicket("123456");
        System.out.println(byTicket);
        UserPassport cairne = userPassportDAO.getByUsername("cairne");
        System.out.println(cairne);
    }

    @Test
    public void testInsert () {
        UserPassport userPassport = new UserPassport(3,"admin1234" , "admin123" , "123123" , "1234567");
        try {
            int insert = userPassportDAO.insert(userPassport);
            System.out.println(insert);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserPassport byTicket = userPassportDAO.getByTicket("123456");
        System.out.println(byTicket);
        UserPassport admin123 = userPassportDAO.getByUsername("admin123");
        System.out.println(admin123);
    }

}
