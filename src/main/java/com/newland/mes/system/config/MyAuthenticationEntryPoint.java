package com.newland.mes.system.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//用来解决匿名用户访问无权限资源时的异常
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletRequest.setAttribute("error","login failed:" + e.getMessage() );
        System.out.println(e.getMessage());
        httpServletRequest.getRequestDispatcher("Mylogin").forward(httpServletRequest,httpServletResponse);
      //  httpServletResponse.getWriter().write("login failed:" + e.getMessage());
    }
}
