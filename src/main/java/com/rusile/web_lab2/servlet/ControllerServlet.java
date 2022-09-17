package com.rusile.web_lab2.servlet;

import com.rusile.web_lab2.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ControllerServlet", value = "/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        if (request.getParameter("x") != null && request.getParameter("y") != null && request.getParameter("r") != null) {
            String x = request.getParameter("x");
            String y = request.getParameter("y");
            String r = request.getParameter("r");
            PrintWriter writer = response.getWriter();
            writer.println("successful");
            try {
                Validator validator = new Validator(x, y, r);
                writer.println("Validator: " + validator);
                writer.println("Check it");
                validator.validateData();
            } catch (Throwable e) {
                writer.println("ERROR");
            }
            writer.println("x:" + x + "; y:" + y + "; r:" + r);
            writer.close();
        } else {
            try (PrintWriter writer = response.getWriter()) {
                writer.println("mismatch:(");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new IllegalStateException("POST request is unavailable. Use GET.");
    }
}
