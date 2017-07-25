package com.liumapp.DNSBee.filter;

import com.liumapp.DNSBee.model.UserPassport;
import com.liumapp.DNSBee.util.RequestThreadUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liumapp on 7/24/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
public class LoginRequireFilter implements Filter {

    private FilterConfig config;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;

        String noCheckPath = config.getInitParameter("noCheckPath");

        if (noCheckPath != null) {
            String[] strArray = noCheckPath.split(";");

            for (int i = 0 ; i < strArray.length ; i++ ) {
                if (strArray[i] == null || "".equals(strArray[i])) continue;

                if (request.getRequestURI().indexOf(strArray[i]) != -1) {
                    filterChain.doFilter(servletRequest , servletResponse);
                    return ;
                }
            }
        }

        UserPassport userPassport = RequestThreadUtils.getUserPassport();
        if (userPassport == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        }

        filterChain.doFilter(servletRequest , servletResponse);
    }

    @Override
    public void destroy() {

    }
}
