package com.controller;

/**
 * Created by Lena on 30.04.2016.
 */
public class AbstractActionFactory {
    private final static ActionFactory instance = new ActionFactory();

    public static ActionFactory getInstance() {
        return instance;
    }
}
