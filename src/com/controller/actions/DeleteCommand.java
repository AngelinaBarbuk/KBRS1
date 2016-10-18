package com.controller.actions;


import com.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by VVV on 27.07.2016.
 */
public class DeleteCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String[] selectedItems = request.getParameterValues("delete");
        try {
            for(String s:selectedItems)
                ArticleService.getInstance().deleteArticle(Integer.parseInt(s));
            session.setAttribute("newsList", ArticleService.getInstance().getArticleList());
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
