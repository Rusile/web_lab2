package com.rusile.web_lab2.filter;

import com.rusile.web_lab2.dao.UserJsonDAO;
import com.rusile.web_lab2.entity.User;
import com.rusile.web_lab2.model.UserDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

@WebFilter(value = {"/index.jsp", "/check-point",  "/controller"})
public class AuthorizationFilter implements Filter {

    private UserDAO dao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        dao = new UserJsonDAO();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String sign = (String) request.getSession().getAttribute("sign");
        if (sign == null) {
            servletRequest.getServletContext().getRequestDispatcher("/authorization.jsp").forward(servletRequest, servletResponse);
            return;
        }

        Optional<User> userOptional = dao.getUserByToken(sign);

        if (!userOptional.isPresent()) {
            servletRequest.getServletContext().getRequestDispatcher("/authorization.jsp").forward(servletRequest, servletResponse);
            return;
        }

        User user = userOptional.get();
        if (user.getValidTill() < System.nanoTime()) {
            servletRequest.getServletContext().getRequestDispatcher("/authorization.jsp").forward(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

}
