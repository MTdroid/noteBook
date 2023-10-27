package org.example;

import lombok.extern.java.Log;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;

import java.io.IOException;
@Log
public class Main {
    public final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws IOException {


        userService.start();



        /*userService.noteNew();
        userService.noteNew();
        userService.noteGetAllNotes();
        userService.noteRemove();
        userService.noteGetAllNotes();
                userService.noteExit();
                userService.start();
                  userService.noteNew();
        userService.noteGetAllNotes();
                */
    }

}