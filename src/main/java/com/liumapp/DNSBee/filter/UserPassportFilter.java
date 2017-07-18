package com.liumapp.DNSBee.filter;

import com.liumapp.DNSBee.model.UserPassport;
import com.liumapp.DNSBee.service.UserPassportSerivce;
import com.liumapp.DNSBee.util.SpringLocator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by liumapp on 7/17/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public class UserPassportFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        UserPassportSerivce userPassportSerivce = SpringLocator.getBean(UserPassportSerivce.class);
        UserPassport userPassport = userPassportSerivce.getUserPassport((HttpServletRequest) request);
        request.setAttribute("userPassport", userPassport);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }

}
