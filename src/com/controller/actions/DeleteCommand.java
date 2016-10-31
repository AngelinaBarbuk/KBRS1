package com.controller.actions;


import com.beans.Article;
import com.controller.Action;
import com.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by VVV on 27.07.2016.
 */
public class DeleteCommand implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = null;
        HttpSession session = request.getSession();
        Integer toDelete = Integer.valueOf(request.getParameter("delete"));
        try {
             ArticleService.getInstance().deleteArticle(toDelete);
            session.setAttribute("newsList", ArticleService.getInstance().getArticleList());
            List<Article> list = ArticleService.getInstance().getArticleList();
            session.setAttribute("newsList",list);
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
