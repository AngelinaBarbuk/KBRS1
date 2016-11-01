package com.controller.actions;

import com.beans.Article;
import com.beans.PermissionType;
import com.beans.User;
import com.controller.Action;
import com.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by VVV on 28.07.2016.
 */
public class AddCommand implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String page = null;
        HttpSession session = request.getSession(true);
        Integer authorId = ((User) session.getAttribute("currentSessionUser")).getId();
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        System.out.println("Add command");

        try {
            Article article = new Article((User) session.getAttribute("currentSessionUser"),title,content);
            int articleId = ArticleService.getInstance().addArticle(article);
            List<User> users = ArticleService.getInstance().getAllUsers();
            for (User user : users) {
                if (authorId != user.getId()) {
                    String[] userPermissions = request.getParameterValues("check_" + user.getId());
                    System.out.println(userPermissions);
                   if (userPermissions != null) {
                       for (String type : userPermissions) {
                           ArticleService.getInstance().addPermission(user.getId(), articleId, Integer.valueOf(type));
                       }
                   }
                } else {
                    List<PermissionType> types = ArticleService.getInstance().getAllPermissionTypes();
                    for (PermissionType permissionType : types) {
                        ArticleService.getInstance().addPermission(article.getAuthor().getId(), articleId, permissionType.getId());
                    }
                }
            }
            List<Article> list = ArticleService.getInstance().getArticleList();//.getArticlesListView(((User) session.getAttribute("currentSessionUser")).getId());
            request.setAttribute("newsList",list);
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
