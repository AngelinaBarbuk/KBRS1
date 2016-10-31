package com.controller.actions;

import com.beans.Article;
import com.beans.PermissionType;
import com.beans.User;
import com.controller.Action;
import com.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by VVV on 28.07.2016.
 */
public class AddFormCommand implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception  {
        List<User> list = ArticleService.getInstance().getAllUsers();
        request.setAttribute("users",list);
        List<PermissionType> list1 = ArticleService.getInstance().getAllPermissionTypes();
        request.setAttribute("permissionTypes",list1);
        return "/addArticle.jsp";
    }
}
