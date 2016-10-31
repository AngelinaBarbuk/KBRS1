package com.controller.actions;

import com.beans.Article;
import com.beans.User;
import com.controller.Action;
import com.controller.controllers.ArticleController;
import com.controller.controllers.LoginController;
import com.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lena on 31.05.2016.
 */
public class DoLogin implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginController loginController = new LoginController();
        ArticleController articleController = new ArticleController();
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        boolean isValid = loginController.checkUser(user);
        System.out.println(user);
        if (isValid) {
            HttpSession session = request.getSession(true);
            session.setAttribute("currentSessionUser",user);
            System.out.println("curr " + session.getAttribute("currentSessionUser"));
            List<Article> articles;
           // articles = articleController.getArticleList();
            articles = ArticleService.getInstance().getArticleList();//.getArticlesListView(user.getId());
            session.setAttribute("newsList",articles);
            System.out.println(articles);
            return "/List.jsp";
        }
        else {
            request.setAttribute("error", "Пользователь или пароль введены неверно!");
            return "/login.jsp";
        }
    }
}
