package com.rusile.web_lab2.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(name = "ErrorHandlerServlet", value = "/error")
public class ErrorHandlerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<html><head><title>Error description</title></head><body>");
            writer.write("<h2>Error description</h2>");
            writer.write("<ul>");
            Arrays.asList(
                            "Code: 400",
                            "Exception type: Bad request",
                            "Message: POST request is not allowed")
                    .forEach(e ->
                            writer.write("<li>" + e + " </li>")
                    );
            writer.write("</ul>");
            writer.write("</html></body>");
        }

    }
}
