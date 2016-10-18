package com.controller.actions;

import com.beans.Article;
import com.beans.User;
import com.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by VVV on 28.07.2016.
 */
public class AddCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws IOException, ServletException {
        String page = null;
        HttpSession session = request.getSession(true);
        Integer authorId = ((User) session.getAttribute("currentSessionUser")).getId();
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        System.out.println("Add command");

        try {
            Article article =new Article(authorId,title,content);
            ArticleService.getInstance().addArticle(article);
            session.setAttribute("newsList",ArticleService.getInstance().getArticleList());
        } catch (SQLException e) {
            page = "/fail.jsp";
            return page;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        page = "/List.jsp";
        return page;
    }
}
