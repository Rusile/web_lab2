package com.rusile.web_lab2.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rusile.web_lab2.table.Coordinates;
import com.rusile.web_lab2.table.Result;
import com.rusile.web_lab2.table.TableHistory;
import com.rusile.web_lab2.utils.HitChecker;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "AreaCheckServlet", value = "/check-point")
public class AreaCheckServlet extends HttpServlet {

    @EJB
    private TableHistory history;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");


        if (request.getAttribute("coordinates") == null || request.getAttribute("startTime") ==null) {
            throw new IllegalStateException("Direct call is not available!");
        }


        Coordinates coordinates = (Coordinates) request.getAttribute("coordinates");
        Result result = new Result(coordinates);
        HitChecker hitChecker = HitChecker.getInstance();
        boolean isHit = hitChecker.isHit(result.getCoordinates());

        result.setHit(isHit);
        result.setCurrentTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        result.setScriptTime((double) (System.nanoTime() - (Long) request.getAttribute("startTime")) / 1000000);
        history.getHistory().add(result);


        PrintWriter writer = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(history);
        writer.println(json);

        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String clearHistoryPar = request.getParameter("clearHistory");
        if (clearHistoryPar != null && clearHistoryPar.equals("true")) {
            history.getHistory().clear();
        }
    }
}
