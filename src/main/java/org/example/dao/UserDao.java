package org.example.dao;

import org.example.model.Note;

import java.util.List;

public interface UserDao {
    void noteHelp();
    Note noteNew();
    List<Note> noteGetAllNotes();
    void noteRemove(long id);
    void noteExport();
    void noteExit();


}
