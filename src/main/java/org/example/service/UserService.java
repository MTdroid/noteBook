package org.example.service;

import org.example.model.Note;

import java.util.List;

public interface UserService {
    void noteHelp();
    Note noteNew();
    List<Note> noteGetAllNotes();
    void noteRemove(long id);
    void noteExport();
    void noteExit();
}
