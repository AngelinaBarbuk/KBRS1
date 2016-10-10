package com.controller.controllers;

import com.beans.User;
import com.controller.DatabaseConnector;

/**
 * Created by Lena on 31.05.2016.
 */
public class UserController {
    DatabaseConnector connector = new DatabaseConnector();

    User getUserById(int id) {
        return connector.getUserById(id);
    }
}
