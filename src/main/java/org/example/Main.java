package org.example;

import org.example.service.UserService;
import org.example.service.UserServiceImpl;

public class Main {
    public final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {

        userService.noteHelp();
        userService.noteNew();
        userService.noteNew();
        userService.noteNew();
    }

}