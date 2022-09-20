package com.rusile.web_lab2.servlet;

import com.rusile.web_lab2.exception.ValidationException;
import com.rusile.web_lab2.table.Coordinates;
import com.rusile.web_lab2.utils.Validator;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Printable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(name = "ControllerServlet", value = "/check-values")
public class ControllerServlet extends HttpServlet {

    @EJB
    private Validator validator;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ValidationException {
        String xPar = request.getParameter("x");
        String yPar = request.getParameter("y");
        String rPar = request.getParameter("r");


        request.setAttribute("startTime", System.nanoTime());


        Coordinates coordinates = validator.validateCoordinates(xPar, yPar, rPar);
        if (coordinates == null) {
            PrintWriter writer = response.getWriter();
            writer.println("Invalid input for x,y,z");
            response.setStatus(400);
            writer.close();
            return;
        }


        request.setAttribute("coordinates", coordinates);
        getServletContext().getRequestDispatcher("/check-point").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clearHistoryPar = request.getParameter("clearHistory");

        if (clearHistoryPar != null && clearHistoryPar.equals("true")) {
            getServletContext().getRequestDispatcher("/check-point").forward(request, response);
        }
    }
}
