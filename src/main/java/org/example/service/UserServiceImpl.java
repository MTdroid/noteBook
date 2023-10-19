package org.example.service;

import org.example.dao.UserDao;
import org.example.dao.UserDaoImpl;
import org.example.model.Note;

import java.util.List;

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
        return null;
    }

    @Override
    public void noteRemove(long id) {

    }

    @Override
    public void noteExport() {

    }

    @Override
    public void noteExit() {

    }
}
