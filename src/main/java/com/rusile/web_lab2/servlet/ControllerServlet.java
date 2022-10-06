package com.rusile.web_lab2.servlet;

import com.rusile.web_lab2.exception.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ControllerServlet", value = "/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ValidationException {

        String xPar = request.getParameter("x");
        String yPar = request.getParameter("y");
        String rPar = request.getParameter("r");

        if (xPar != null && yPar != null && rPar != null) {
            getServletContext().getRequestDispatcher("/check-point").forward(request, response);
        } else {
            PrintWriter writer = response.getWriter();
            writer.println("Invalid request!");
            response.setStatus(400);
            writer.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ValidationException {
        getServletContext().getRequestDispatcher("/check-point").forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clearHistoryPar = request.getParameter("clearHistory");

        if (clearHistoryPar != null) {
            getServletContext().getRequestDispatcher("/check-point").forward(request, response);
        } else {
            try (PrintWriter writer = response.getWriter()) {
                writer.println("Invalid request!");
                response.setStatus(400);
            }
        }

    }
}