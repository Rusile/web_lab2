package com.rusile.web_lab2.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rusile.web_lab2.exception.ValidationException;
import com.rusile.web_lab2.table.Coordinates;
import com.rusile.web_lab2.table.ResultRow;
import com.rusile.web_lab2.table.TableHistoryBean;
import com.rusile.web_lab2.utils.HitChecker;
import com.rusile.web_lab2.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "AreaCheckServlet", value = "/check-point")
public class AreaCheckServlet extends HttpServlet {

    private TableHistoryBean history;

    private HitChecker hitChecker;

    private Validator validator;

    @Override
    public void init() throws ServletException {
        super.init();
        hitChecker = new HitChecker();
        validator = new Validator();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        history = (TableHistoryBean) session.getAttribute("history");
        if (history == null) history = new TableHistoryBean();


        long startTime = System.nanoTime();

        response.setContentType("application/json; charset=UTF-8");
        String xPar = request.getParameter("x");
        String yPar = request.getParameter("y");
        String rPar = request.getParameter("r");

        Coordinates coordinates;
        try {
            validator.validateCoordinates(xPar, yPar, rPar);
            coordinates = new Coordinates(Integer.parseInt(xPar), Double.parseDouble(yPar), Float.parseFloat(rPar));
        } catch (ValidationException e) {
            log(e.getMessage());
            PrintWriter writer = response.getWriter();
            writer.println(e.getMessage());
            response.setStatus(400);
            writer.close();
            return;
        }

        ResultRow result = new ResultRow(coordinates);
        boolean isHit = hitChecker.isHit(result.getCoordinates());

        result.setHit(isHit);
        result.setCurrentTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        result.setScriptTime((double) (System.nanoTime() - startTime) / 1000000);
        history.getHistory().add(result);

        session.setAttribute("history", history);


        PrintWriter writer = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(history);
        writer.println(json);

        writer.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        HttpSession session = request.getSession();
        history = (TableHistoryBean) session.getAttribute("history");
        if (history == null) history = new TableHistoryBean();

        PrintWriter writer = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(history);
        writer.println(json);

        writer.close();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String clearHistoryPar = request.getParameter("clearHistory");
        if (clearHistoryPar != null && clearHistoryPar.equals("true")) {
            history.getHistory().clear();
        }
    }
}