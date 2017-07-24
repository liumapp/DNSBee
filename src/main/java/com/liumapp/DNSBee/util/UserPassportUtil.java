package com.liumapp.DNSBee.util;

import com.liumapp.DNSBee.model.UserPassport;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public class UserPassportUtil {

    private static Random random = new Random();

    public static String salt(String password, String salt) {
        return DigestUtils.md5DigestAsHex((password + salt).getBytes());
    }

    public static boolean checkPassword(UserPassport userPassport, String password) {
        if (salt(password, userPassport.getSalt()).equals(userPassport.getPasswordSalt())) {
            return true;
        } else {
            return false;
        }
    }

    public static String generateTicket(String email) {
        return DigestUtils.md5DigestAsHex((email + random.nextInt() + System.currentTimeMillis())
                .getBytes());
    }

    public static String generateSalt() {
        return StringUtils.substring(DigestUtils.md5DigestAsHex(("" + random.nextInt() + System
                .currentTimeMillis()).getBytes()), 20);
    }

    public static String generateUserNumber() {
        int rdNumber = (int) Math.round(Math.random() * 8999 + 1000);
        return "LM" + new Date().getTime() + rdNumber;
    }

    public static String getTicketFromCookie(HttpServletRequest request) {
        String ticket = CookieUtils.getCookie(request, CookieUtils.TICKET_KEY);
        return ticket;
    }

    public static void saveUserPassportCookie(HttpServletResponse response, UserPassport userPassport) {
        CookieUtils.saveCookie(response, CookieUtils.TICKET_KEY, userPassport.getTicket(),
                (int) TimeUnit.DAYS.toSeconds(30), "/");
    }

    public static void removeUserPassportCookie(HttpServletResponse response , UserPassport userPassport) {
        CookieUtils.removeCookie(response , CookieUtils.TICKET_KEY , "/");
    }

}
