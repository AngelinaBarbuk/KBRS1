package com.controller.actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by VVV on 26.07.2016.
 */
public interface ActionCommand {
    String execute(HttpServletRequest request) throws IOException, ServletException;
}
