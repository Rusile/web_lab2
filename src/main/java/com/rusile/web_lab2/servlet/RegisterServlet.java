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


@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

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
            log("1");
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

        if (userOptional.isPresent()) {
            req.setAttribute("error", "User exists");
            System.out.println(userOptional.get());
            log("2");
            PrintWriter writer = resp.getWriter();
            writer.print("User exists");
            resp.setStatus(400);
            writer.close();
            return;
        }

        req.getSession().setAttribute("sign", sign);
        userDAO.saveUser(new User(login, password, System.nanoTime() +  TimeUnit.SECONDS.toNanos(30), sign));
        req.getServletContext().getRequestDispatcher("/authorization.jsp").forward(req, resp);
    }
}
