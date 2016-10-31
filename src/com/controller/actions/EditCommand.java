package com.controller.actions;


import com.beans.Article;
import com.controller.Action;
import com.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by VVV on 01.08.2016.
 */
public class EditCommand implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = null;
        HttpSession session = request.getSession();
       // Article oldNews = (Article) session.getAttribute("news");
        Article news = new Article();
        String title = request.getParameter("title");
        if(title!=null)
            news.setName(title);

        String content = request.getParameter("content");
        if(content!=null)
            news.setText(content);
        news.setId(Integer.valueOf(request.getParameter("id")));
        try {
            ArticleService.getInstance().editArticle(news);
            //ArticleService.getInstance().deleteArticle(oldNews.getId());
            //ArticleService.getInstance().addArticle(new News(title,author,date,content,imgPath));
            session.setAttribute("newsList",ArticleService.getInstance().getArticleList());
        } catch (SQLException e) {
            e.printStackTrace();
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
