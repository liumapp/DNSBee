package us.codecraft.blackhole.suite.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import us.codecraft.blackhole.suite.exception.RegisterException;
import us.codecraft.blackhole.suite.model.UserPassport;
import us.codecraft.blackhole.suite.service.UserPassportSerivce;

/**
 * Created by liumapp on 7/10/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext*.xml"})
public class RegisterControllerTest {

    @Autowired
    private UserPassportSerivce userPassportSerivce;

    @Test
    public void test() {
        try {
            UserPassport userPassport = userPassportSerivce.addUserPassport("admin888" , "adminadmin");
            System.out.println(userPassport);
        } catch (RegisterException e) {
            e.printStackTrace();
        }
    }

}
