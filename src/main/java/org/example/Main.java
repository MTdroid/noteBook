package org.example;

import lombok.extern.java.Log;
import org.example.dao.UserDao;
import org.example.dao.UserDaoImpl;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;

import java.io.IOException;
@Log
public class Main {
    public final static UserService userService = new UserServiceImpl();
    public final static UserDao userDao = new UserDaoImpl();

    public static void main(String[] args) throws IOException {


        userService.startNotebook();




        /*userService.createNewNote();
        userService.createNewNote();
        userService.GetAllNotes();
        userService.noteRemove();
        userService.GetAllNotes();
                userService.noteExit();
                userService.startNotebook();
                  userService.createNewNote();
        userService.GetAllNotes();
                */
    }

}