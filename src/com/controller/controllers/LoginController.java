package com.controller.controllers;

import com.beans.User;
import com.controller.DatabaseConnector;

/**
 * Created by Lena on 31.05.2016.
 */
public class LoginController {
    DatabaseConnector connector = new DatabaseConnector();

    public  boolean checkUser(User user) {
        return connector.checkUser(user);
    }
}
