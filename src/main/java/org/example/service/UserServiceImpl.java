package org.example.service;

import lombok.extern.java.Log;
import org.example.dao.UserDao;
import org.example.dao.UserDaoImpl;
import org.example.model.Note;

import java.io.IOException;
import java.util.List;
@Log

public class UserServiceImpl implements UserService {
    private final UserDao daoNote = new UserDaoImpl();
    @Override
    public void noteHelp() {
        daoNote.noteHelp();

    }

    @Override
    public Note noteNew() {
        return daoNote.noteNew();

    }

    @Override
    public List<Note> noteGetAllNotes() {

        return daoNote.noteGetAllNotes();
    }

    @Override
    public void noteRemove() {
        daoNote.noteRemove();

    }

    @Override
    public void noteExport() throws IOException {
        daoNote.noteExport();

    }

    @Override
    public void start() throws IOException {
        daoNote.start();
    }

}
