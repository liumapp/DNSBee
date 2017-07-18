package com.liumapp.DNSBee.exception;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public class RegisterException extends Exception {

    private static final long serialVersionUID = 1L;

    private final String code;

    public RegisterException(String code) {
        super(code);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
