package com.controller.actions;


import com.beans.Article;
import com.controller.Action;
import com.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by VVV on 01.08.2016.
 */
public class EditFormCommand implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = null;
        int number = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
       /* List<Article> list = (List<Article>) session.getAttribute("newsList");
        session.setAttribute("news",list.get(number));*/
       request.setAttribute("news", ArticleService.getInstance().getArticle(number));
        page = "/editArticle.jsp";
        return page;
    }
}
