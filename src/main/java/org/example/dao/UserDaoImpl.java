package org.example.dao;

import lombok.extern.java.Log;
import org.example.model.Note;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Log
public class UserDaoImpl implements UserDao {

    /*fine в формате: «вызвана команда {commandName}».
    info и описанием ошибки, например: «текст заметки должен быть длиннее 3 символов, введено - {данные от пользователя}».
    Логирование ошибок при вызове некорректной команды и исключений
            (например, IOException) с уровнем warning.*/
    @Override
    public void noteHelp() {  //OK

        log.fine("вызвана команда help");
        System.out.println("""
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
    public Note noteNew() { //OK
        log.fine("вызвана команда note-new");
    Note note = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите заметку");
        String text = scanner.nextLine();
        try {
            if (text.length() <3){
                log.info("текст заметки должен быть длиннее 3 символов, вы ввели - " + text);
               throw new IllegalArgumentException("текст заметки должен быть длиннее 3 символов, вы ввели - " + text);
            }

            log.fine("введите label");
            System.out.println("Добавить метки? Метки состоят из одного слова и могу содержать только буквы.\nДля добавления нескольких меток разделяйте слова пробелом.");
            String labelInput =scanner.nextLine();

            List<String> labelSplit = List.of(labelInput.split(" "));
            List<String> labels  = new ArrayList<>();

            for (String filter : labelSplit) {

                    if (filter.matches("^[a-zA-Z]+$")) {
                    labels.add(filter.toUpperCase());
                    } else if (filter.matches("")){
                        labels.add("");
                    } else {
                        log.info("Метки состоят из одного слова и могу содержать только буквы, вы ввели - "+ labelInput);
                        throw new IllegalArgumentException("НЕПРАВИЛЬНЫЙ ТЭГ");

                    }

            }
                //reg ex лейблов !!!!!!
                //состоят из одного слова и могу содержать только буквы.
                // Для добавления нескольких меток разделяйте слова пробелом.»
                // -> вводим набор меток.
                // (получаем метки, проверяем что они введены только слова, состоящие из букв, без других символов)
                // (используйте тут регулярные выражения)

            note = new Note(text,labels);
            Note.getNoteList().add(note);
            log.fine("Заметка успешно добавлена" + note);
            System.out.println(note);



        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return note;
        }

    @Override
    public List<Note> noteGetAllNotes() { //OK
        log.fine("вызвана команда note-list");
        System.out.println("Введите метки, чтобы отобразить определенные заметки или оставьте пустым для отображения всех заметок");
        List<Note> listNote = new ArrayList<>();
        List<String> labels;
        Scanner scanner = new Scanner(System.in);
        String label = scanner.nextLine();

        if (Objects.equals(label, "")) {
            listNote.addAll(Note.getNoteList());
            for (Note note : listNote) {
                System.out.println(note);
            }
            return listNote;

        } else {

        labels =List.of(label.toUpperCase().split(" "));
        for (Note note : Note.getNoteList()) {
            for (String filter : labels){
            if(note.getLabel().contains(filter)) {
                listNote.add(note);

            } else {
                log.info("Введите существующий label или оставьте поле пустым. Вы ввели - " + label);
            }
            }
        }
        for (Note note : listNote) {
            System.out.println(note);
        }
        return listNote;
        }

    }

    @Override
    public void noteRemove() { // OK
        log.fine("вызвана команда note-remove");
        System.out.println("введите id удаляемой заметки");
        Scanner scanner = new Scanner(System.in);
        String idRemove = scanner.nextLine();

        try {
            int id= Integer.parseInt(idRemove);

            Iterator<Note> iterator = Note.getNoteList().iterator();
            while (iterator.hasNext()){
                Note note =iterator.next();
                if (note.getId() == id){
                    iterator.remove();
                    System.out.println("Заметка с id "+idRemove+" удалена");
                }

            }
            } catch (NumberFormatException e){
            log.info("Введите существующий ID! Вы ввели - " + idRemove);


        }
        }

    @Override
    public void noteExport() throws IOException { //дату
        log.fine("вызвана команда note-export");
        DateFormat dateFormat =new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
        String filetowrite = "C:\\Users\\M.TAG\\Downloads\\export\\notes_"+dateFormat.format(new Date())+".txt";
        try {
            FileWriter fw = new FileWriter(filetowrite);
            for (Note note: Note.getNoteList()){
                fw.write(note +"");
                log.info("Экспорт прошел успешно");
            }
            fw.close();
        } catch (Exception e){
            log.warning("Ошибка экспорта");
        }


    }


    public void start() throws IOException { // OK
        System.out.println("Это Ваша записная книжка. Вот список доступных команд: help, note-new, note-list, note-remove, note-export, exit.");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String notepad = scanner.nextLine();
            switch (notepad) {
                case "help": noteHelp();
                    continue;
                case "note-new": noteNew();
                    continue;
                case "note-remove": noteRemove();
                    continue;
                case "note-export": noteExport();
                    continue;
                case "note-list": noteGetAllNotes();
                    continue;
                case "exit": log.fine("вызвана команда help"); System.exit(0);
                default : log.warning("команда не найдена");
            }}
            }
}
