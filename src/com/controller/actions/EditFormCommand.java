package com.controller.actions;


import com.beans.Article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by VVV on 01.08.2016.
 */
public class EditFormCommand implements ActionCommand  {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        int number = Integer.parseInt(request.getParameter("number"));
        HttpSession session = request.getSession();
        List<Article> list = (List<Article>) session.getAttribute("newsList");
        session.setAttribute("news",list.get(number));
        page = "/editArticle.jsp";
        return page;
    }
}
