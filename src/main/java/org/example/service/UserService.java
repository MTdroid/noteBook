package org.example.service;

import org.example.model.Note;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void noteHelp();

    Note createNewNote();

    List<Note> getAllNotes();

    void noteRemove();

    void noteExport() throws IOException;

    void startNotebook() throws IOException;
}
