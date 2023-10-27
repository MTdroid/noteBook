package org.example.dao;

import org.example.model.Note;

import java.io.IOException;
import java.util.List;

public interface UserDao {
    void noteHelp();
    Note noteNew();
    List<Note> noteGetAllNotes();
    void noteRemove();
    void noteExport() throws IOException;
    void start() throws IOException;


}
