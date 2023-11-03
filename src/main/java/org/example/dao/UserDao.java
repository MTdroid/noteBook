package org.example.dao;

import org.example.model.Note;

import java.io.IOException;
import java.util.List;

public interface UserDao {


    Note createNewNote(String text, List<String> label);

    void GetAllNotes(List<Note> listNote);

    void noteRemove(int id);

    void noteExport(String link) throws IOException;


}
