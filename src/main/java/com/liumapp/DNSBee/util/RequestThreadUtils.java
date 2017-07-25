package com.liumapp.DNSBee.util;

import com.liumapp.DNSBee.model.UserPassport;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public class RequestThreadUtils {

    private final static String PASSPORT_KEY = "userPassport";

    public static String getPassportKey() {
        return PASSPORT_KEY;
    }

    public static UserPassport getUserPassport() {
        return getAttribute(PASSPORT_KEY, UserPassport.class);
    }

    public static void setUserPassport(UserPassport userPassport) {
        setAttribute(PASSPORT_KEY, userPassport);
    }

    public static String getAttribute(String name) {
        return getAttribute(name, String.class);
    }

    public static <T> T getAttribute(String name, Class<T> clazz) {
        return clazz.cast(RequestContextHolder.currentRequestAttributes().getAttribute(name,
                RequestAttributes.SCOPE_REQUEST));
    }

    public static <T> void setAttribute(String name, T t) {
        RequestContextHolder.currentRequestAttributes().setAttribute(name, t,
                RequestAttributes.SCOPE_REQUEST);
    }

}
