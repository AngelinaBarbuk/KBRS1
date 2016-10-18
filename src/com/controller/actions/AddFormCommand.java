package com.controller.actions;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by VVV on 28.07.2016.
 */
public class AddFormCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        return "/addArticle.jsp";
    }
}
