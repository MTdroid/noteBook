package org.example.dao;

import lombok.extern.java.Log;
import org.example.model.Note;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Log
public class UserDaoImpl implements UserDao {

    @Override
    public Note createNewNote(String text, List<String> label) {
        Note note = new Note(text, label);
        List<String> labels = new ArrayList<>();
        labels.add(label.toString());

        Note.getNoteList().add(note);
        return note;
    }


    @Override
    public void GetAllNotes(List<Note> listNote) {
        for (Note note : listNote) {
            log.info(String.valueOf(note));

        }
    }


    @Override
    public void noteRemove(int id) {

        Iterator<Note> iterator = Note.getNoteList().iterator();
        while (iterator.hasNext()) {
            try {
                Note note = iterator.next();
                if (note.getId() == id) {
                    iterator.remove();
                    log.info("Заметка с id - "+id+" удалена");
                }
            } catch (IllegalArgumentException e) {
                log.info("заметка не найдена");
            }

        }
    }
    @Override
    public void noteExport(String link) throws IOException {

        FileWriter fw = new FileWriter(link);
        for (Note note : Note.getNoteList()) {
            fw.write(note + "");
        }
        fw.close();
    }


}
