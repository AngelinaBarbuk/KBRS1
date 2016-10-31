package com.servlet;


import com.controller.actions.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class ArticleServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }


    /*

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Post");

        String act = req.getParameter("act");
        switch (act){
            case "View":{
                int number = Integer.parseInt(req.getParameter("number"));
                //List<News> list = (List<News>) req.getAttribute("listNews");
                req.setAttribute("number",number);
                req.getRequestDispatcher("/news.jsp").forward(req, resp);
                break;
            }
            case "Edit":{
                break;
            }case "Delete selected":{
                String[] selectedItems = req.getParameterValues("id");
                for(String s:selectedItems){
                    System.out.println(s);
                    try {
                        newsDAO.deleteArticle(Integer.parseInt(s));
                        req.getSession().setAttribute("newsList", newsDAO.getListNews());
                        //req.setAttribute("id",Integer.parseInt(s));
                        req.getRequestDispatcher("/List.jsp").forward(req, resp);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (PropertyVetoException e) {
                        e.printStackTrace();
                    }
                }

                break;
            }
        }


    }


*/


    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = "";
        // определение команды, пришедшей из JSP
        String action = request.getParameter("command");

        if(action==null)
            action="";
        ActionCommand command = null;
        switch (action) {
            case "login":
                //command = new LoginCommand();
                break;
            case "logout":
                //command = new LogoutCommand();
                break;
            case "GetList":
                command = new ListCommand();
                break;
            case "View":
                command = new ViewCommand();
                break;
            case "DeleteSelected":
                command=new DeleteCommand();
                break;
            case "AddForm":
                command = new AddFormCommand();
                break;
            case "AddNews":
                command = new AddCommand();
                break;
            case "EditForm":
                command = new EditFormCommand();
                break;
            default:
                command = new ListCommand();
                //command = new EmptyCommand();
                break;
        }

        page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            // вызов страницы ответа на запрос
            dispatcher.forward(request, response);

        } else {
            // установка страницы c cообщением об ошибке
            page = "List.jsp";
            request.getSession().setAttribute("nullPage", "Page not found. Business logic error.");
            response.sendRedirect(request.getContextPath() + page);

        }
    }
}
