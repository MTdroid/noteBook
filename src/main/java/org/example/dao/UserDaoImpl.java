package org.example.dao;

import lombok.extern.java.Log;
import org.example.model.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Log
public class UserDaoImpl implements UserDao {

    /*fine в формате: «вызвана команда {commandName}».
    info и описанием ошибки, например: «текст заметки должен быть длиннее 3 символов, введено - {данные от пользователя}».
    Логирование ошибок при вызове некорректной команды и исключений
            (например, IOException) с уровнем warning.*/
    @Override
    public void noteHelp() {
        /*log.info("haha");*/
        log.fine("help");
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
    public Note noteNew() {
log.fine("noteNew");
    Note note = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println(" text");
        String text = scanner.nextLine();
        try {
            if (text.length() <3){
                log.fine("text<3" + text);
               throw new Exception();
            }
            log.fine("labels");
            System.out.println("labels sout");
            String labelInput =scanner.nextLine();

            List<String> labelSplit = List.of(labelInput.split(" "));
            List<String> labels  = new ArrayList<>();

            for (String filter : labelSplit) {

                    if (filter.matches("[a-zA-Z]")) {
                    labels.add(filter.toUpperCase());
                    } else {
                        log.info("ERR" + labelInput);
                    }




                //reg ex лейблов !!!!!!
                //состоят из одного слова и могу содержать только буквы.
                // Для добавления нескольких меток разделяйте слова пробелом.»
                // -> вводим набор меток.
                // (получаем метки, проверяем что они введены только слова, состоящие из букв, без других символов)
                // (используйте тут регулярные выражения)
            }
            note = new Note(text,labels);
            Note.getNoteList().add(note);
            log.fine("note ok" + note);



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(note);
        return note;
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
