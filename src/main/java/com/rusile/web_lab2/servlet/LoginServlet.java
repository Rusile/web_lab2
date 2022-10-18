package com.rusile.web_lab2.servlet;

import com.rusile.web_lab2.dao.UserJsonDAO;
import com.rusile.web_lab2.entity.User;
import com.rusile.web_lab2.model.UserDAO;
import com.rusile.web_lab2.utils.Encryptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private Encryptor encryptor;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        encryptor = new Encryptor();
        userDAO = new UserJsonDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("login") == null || req.getParameter("password") == null || req.getParameter("login").equals("") || req.getParameter("password").equals("")) {
            PrintWriter writer = resp.getWriter();
            writer.print("Login and password must be not null");
            resp.setStatus(400);
            writer.close();
            return;
        }
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String sign = encryptor.encryptString(login + password);

        Optional<User> userOptional = userDAO.getUserByToken(sign);

        if (!userOptional.isPresent()) {
            PrintWriter writer = resp.getWriter();
            writer.print("Login or password are wrong");
            resp.setStatus(400);
            writer.close();
            return;
        }


        userDAO.updateUser(new User(login, password, System.nanoTime() + TimeUnit.SECONDS.toNanos(30), sign));
        req.getSession().setAttribute("sign", sign);
        req.getServletContext().getRequestDispatcher("/authorization.jsp").forward(req, resp);
    }
}
