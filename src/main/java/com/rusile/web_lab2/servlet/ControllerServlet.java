package com.rusile.web_lab2.servlet;

import com.rusile.web_lab2.exception.ValidationException;
import com.rusile.web_lab2.table.Coordinates;
import com.rusile.web_lab2.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerServlet", value = "/check-values")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ValidationException {
        String xPar = request.getParameter("x");
        String yPar = request.getParameter("y");
        String rPar = request.getParameter("r");
        String clearHistoryPar = request.getParameter("clearHistory");

        if (clearHistoryPar != null && clearHistoryPar.equals("true")) {
            getServletContext().getRequestDispatcher("/check-point").forward(request, response);
            return;
        }

        if (xPar != null && yPar != null && rPar != null) {
            request.setAttribute("startTime", System.nanoTime());

            Validator validator = Validator.getInstance();
            Coordinates coordinates = validator.validateCoordinates(xPar, yPar, rPar);


            request.setAttribute("coordinates", coordinates);
            getServletContext().getRequestDispatcher("/check-point").forward(request, response);
        } else {
            throw new ValidationException("X, Y, Z and startTime fields must be not null!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new IllegalStateException("POST request is unavailable. Use GET.");
    }
}
