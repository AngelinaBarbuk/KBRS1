package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lena on 30.04.2016.
 */
public interface Action {
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
