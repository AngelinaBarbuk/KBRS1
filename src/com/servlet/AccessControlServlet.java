package com.servlet;

import com.controller.AbstractActionFactory;
import com.controller.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Lena on 31.05.2016.
 */
@WebServlet(name = "AccessControlServlet")
public class AccessControlServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service1(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service1(request, response);
    }

    protected void service1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Action action = AbstractActionFactory.getInstance().getAction(request);
            String view = action.execute(request, response);
            if (view != null) getServletContext().getRequestDispatcher(view).forward(request, response);
        } catch (Exception e) {
            throw new ServletException("Executing action failed.", e);
        }
    }
}
