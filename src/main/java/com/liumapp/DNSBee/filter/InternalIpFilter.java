package com.liumapp.DNSBee.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liumapp on 8/2/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public class InternalIpFilter implements Filter{

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;

        String ips = config.getInitParameter("ip");

        if (ips != null) {
            String[] strArray = ips.split(";");

            for (int i = 0 ; i < strArray.length ; i++ ) {
                if (strArray[i] == null || "".equals(strArray[i])) continue;
                String remoteIp = request.getRemoteHost();
                if (remoteIp.indexOf(strArray[i]) != -1) {
                    filterChain.doFilter(servletRequest , servletResponse);
                    return ;
                }

            }
        }

        response.sendRedirect(request.getContextPath() + "/login");

        return;

    }

    @Override
    public void destroy() {

    }

}
