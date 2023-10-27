package org.example.service;

import org.example.model.Note;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void noteHelp();
    Note noteNew();
    List<Note> noteGetAllNotes();
    void noteRemove();
    void noteExport() throws IOException;
    void start() throws IOException;
}
