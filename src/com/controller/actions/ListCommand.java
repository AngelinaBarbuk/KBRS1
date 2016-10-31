package com.controller.actions;

import com.beans.Article;
import com.beans.User;
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
 * Created by B on 17.10.2016.
 */
public class ListCommand implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = null;
        // извлечение из запроса выбора курса обучения
        HttpSession session = request.getSession();
        try {
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

