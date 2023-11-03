package org.example.service;

import lombok.extern.java.Log;
import org.example.dao.UserDao;
import org.example.dao.UserDaoImpl;
import org.example.model.Note;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Log

public class UserServiceImpl implements UserService {
    private final UserDao daoNote = new UserDaoImpl();

    @Override
    public void noteHelp() {

        log.info("вызвана команда help");
        log.info("""
                 Это Ваша записная книжка. Вот список доступных команд:\s
                 help - выводит на экран список доступных команд с их описанием\s
                 note-new - создать новую заметку\s
                 note-list - вывести весь список заметок\s
                 note-remove - удалить заметку\s
                 note-export - поместить все заметки в текстовый файл\s
                 exit - завершить работу приложения\
                """);
    }

    @Override
    public Note createNewNote() {
        log.info("вызвана команда note-new");

        Scanner scanner = new Scanner(System.in);
        log.info("Введите заметку");
        String text = scanner.nextLine();
        Note note = null;
        try {
            textVerification(text);
            log.info("Добавить метки? Метки состоят из одного слова и могу содержать только буквы.\nДля добавления нескольких меток разделяйте слова пробелом.");
            String labelInput = scanner.nextLine();
            List<String> labelSplit = List.of(labelInput.toUpperCase().split(" "));
            labelVerification(labelSplit);

            note = daoNote.createNewNote(text, labelSplit);
            log.info("Заметка успешно добавлена\n" + note);


        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return note;
    }

    public static void labelVerification(List<String> labelSplit) throws IllegalArgumentException {
        for (String filter : labelSplit) {
            if (filter.matches("^[a-zA-Z]+$")) {
                return;
            } else if (filter.matches("")) {
                return;
            } else {
                log.info("Метки состоят из одного слова и могу содержать только буквы. Для добавления нескольких меток разделяйте слова пробелом, вы ввели - " + filter);
            }
        }
        throw new IllegalArgumentException("");
    }

    public static void textVerification(String text) throws IllegalArgumentException {
        if (text.length() > 3) {
            log.fine("текст >3 символов");
        } else {
            log.info("текст заметки должен быть длиннее 3 символов, вы ввели - " + text);
            throw new IllegalArgumentException("");
        }

    }

    @Override
    public List<Note> GetAllNotes() {

        log.info("вызвана команда note-list");
        log.info("Введите метки, чтобы отобразить определенные заметки или оставьте пустым для отображения всех заметок");
        List<Note> listNote = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String label = scanner.nextLine();

        if (Objects.equals(label, "")) {

            listNote.addAll(Note.getNoteList());
            daoNote.GetAllNotes(listNote);
            return listNote;
        } else {
            return GetNotesByLabel(label);
        }
    }

    public List<Note> GetNotesByLabel(String label) {
        List<Note> listNote = new ArrayList<>();
        List<String> labels = List.of(label.toUpperCase().split(" "));
        for (Note note : Note.getNoteList()) {
            for (String filter : labels) {
                if (note.getLabel().contains(filter)) {
                    listNote.add(note);

                } else {
                    log.info("Введите существующий label или оставьте поле пустым. Вы ввели - " + label);
                }
            }
        }
        daoNote.GetAllNotes(listNote);
        {

        }
        return listNote;
    }

    @Override
    public void noteRemove() {
        log.info("вызвана команда note-remove");
        log.info("введите id удаляемой заметки");
        Scanner scanner = new Scanner(System.in);
        String idRemove = scanner.nextLine();

        try {
            daoNote.noteRemove(Integer.parseInt(idRemove));

        } catch (NumberFormatException | NoSuchElementException e) {
            log.info("Введите корректный ID! Вы ввели - " + idRemove);

        }

    }

    @Override
    public void noteExport() throws IOException {
        log.info("вызвана команда note-export");
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
        String filetowrite = "C:\\Users\\M.TAG\\Downloads\\export\\notes_" + dateFormat.format(new Date()) + ".txt";
        try {
            if (Note.getNoteList().isEmpty()) {
                log.info("Лист заметок пуст!");
            } else {
                daoNote.noteExport(filetowrite);
                log.info("Экспорт прошел успешно");
            }

        } catch (Exception e) {
            log.warning("Ошибка экспорта");
        }

    }


    public void startNotebook() throws IOException {
        log.info("Это Ваша записная книжка. Вот список доступных команд: help, note-new, note-list, note-remove, note-export, exit.");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String notepad = scanner.nextLine();
            switch (notepad) {
                case "help":
                    noteHelp();
                    continue;
                case "note-new":
                    createNewNote();
                    continue;
                case "note-remove":
                    noteRemove();
                    continue;
                case "note-export":
                    noteExport();
                    continue;
                case "note-list":
                    GetAllNotes();
                    continue;
                case "exit":
                    log.info("вызвана команда exit");
                    System.exit(0);
                default:
                    log.warning("команда не найдена");
            }
        }
    }

    private static final AtomicLong idCounter = new AtomicLong();

    public static Integer GenerateID() {
        return (int) idCounter.getAndIncrement();

    }
}
