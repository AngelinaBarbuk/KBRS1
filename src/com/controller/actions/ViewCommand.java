package com.controller.actions;


import com.beans.Article;
import com.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by VVV on 26.07.2016.
 */
public class ViewCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        // извлечение из запроса выбора курса обучения
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Article article = ArticleService.getInstance().getArticle(id);
            session.setAttribute("article", article);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        page = "/news.jsp";
        return page;
    }
}
