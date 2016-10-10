package com.controller.actions;

import com.beans.User;
import com.controller.Action;
import com.controller.controllers.LoginController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Lena on 31.05.2016.
 */
public class DoLogin implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LoginController controller = new LoginController();
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("password"));
        boolean isValid = controller.checkUser(user);
        System.out.println(user);
        if (isValid) {
            HttpSession session = request.getSession(true);
            session.setAttribute("currentSessionUser",user);
            return "/jsp/main.jsp";
        }
        else {
            request.setAttribute("error", "Пользователь или пароль введены неверно!");
            return "/jsp/login.jsp";
        }
    }
}
