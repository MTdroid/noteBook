import org.example.model.Note;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestNote {

    @Mock
    private final UserService userService = new UserServiceImpl();
    @Test
    @DisplayName("Создание корректной заметки с 1-ым лейблом")
    public void noteWithTextAnd1Label() {
        InputStream text = new ByteArrayInputStream("aaaa\nabc".getBytes());
        System.setIn(text);
        Note note = userService.createNewNote();

        assertEquals("aaaa", note.getText());
        assertEquals((List.of("ABC")), note.getLabel());

    }

    @Test
    @DisplayName("Создание корректной заметки с 2-мя лейблами")
    public void noteWithTwoLabels() {
        InputStream text = new ByteArrayInputStream("aaaa\nabc cba".getBytes());
        System.setIn(text);
        Note note = userService.createNewNote();

        assertEquals("aaaa", note.getText());
        assertEquals(List.of("ABC", "CBA"), note.getLabel());
    }
    @Test
    @DisplayName("Создание  заметки c некорректным лейблом")
    public void noteWithoutLabels() {
        InputStream text = new ByteArrayInputStream("aaaa\n231".getBytes());
        System.setIn(text);
        Note note = userService.createNewNote();
        assertFalse(Note.getNoteList().contains(note));
    }
    @Test
    @DisplayName("Создание некорректной заметки с текстом <3 символов")
    public void noteTextWithLessThan3Char()
    {
        InputStream text = new ByteArrayInputStream("a\nabc".getBytes());
        System.setIn(text);
        Note note = userService.createNewNote();
        assertNull(note);
    }
    @Test
    @DisplayName("note-list с вводом лейбла")
    public void showListNoteFilterByLable() {
        InputStream text = new ByteArrayInputStream("aaa\nasf asd".getBytes());
        System.setIn(text);
        Note note = userService.createNewNote();
        InputStream text1 = new ByteArrayInputStream("sssss\nabab".getBytes());
        System.setIn(text1);
        Note note1 = userService.createNewNote();

        InputStream inputStream = new ByteArrayInputStream("ABAB".getBytes());
        System.setIn(inputStream);
        assertEquals(1, userService.getAllNotes().size());
    }

    @Test
    @DisplayName("Удаление существующей заметки")
    public void removeExistNoteById() {
        InputStream text = new ByteArrayInputStream("removeNote\nabc".getBytes());
        System.setIn(text);
        Note note = userService.createNewNote();

        System.out.println(note);
        InputStream inputStream = new ByteArrayInputStream(String.valueOf(note.getId()).getBytes());
        System.setIn(inputStream);
        userService.noteRemove();
        assertFalse(Note.getNoteList().contains(note));
    }
    @Test
    @DisplayName("Удаление заметки с некорректным вводом")
    public void removeInvalidId() {
        InputStream text = new ByteArrayInputStream("removeNote\nabc".getBytes());
        System.setIn(text);
        Note note = userService.createNewNote();
        InputStream inputStream = new ByteArrayInputStream(String.valueOf("abc ").getBytes());
        System.setIn(inputStream);
        userService.noteRemove();
        assertTrue(Note.getNoteList().contains(note));

    }

    @Test
    @DisplayName("Удаление несуществующей заметки")
    public void removeNotExistNoteById() {
        InputStream text = new ByteArrayInputStream("removeNote\nabc".getBytes());
        System.setIn(text);
        Note note = userService.createNewNote();
        InputStream inputStream = new ByteArrayInputStream(String.valueOf("19").getBytes());
        System.setIn(inputStream);
        userService.noteRemove();
        assertThrows(NoSuchElementException.class, userService::noteRemove);

    }
    @Test
    @DisplayName("Корректность тела заметки")
    public void correctBody() {
        InputStream text = new ByteArrayInputStream("testForm\nabc".getBytes());
        System.setIn(text);
        Note note = userService.createNewNote();
        assertEquals("{" + note.getId() + "}" + " # {testForm}\n" +
                "{ABC}\n" +
                " \n" +
                "=================== \n", note.toString());
    }
    @Test
    @DisplayName("Проверка уникальности id")
    public void uniqueId() {//OK
        InputStream text = new ByteArrayInputStream("dfsgvfd\nabc".getBytes());
        System.setIn(text);
        Note note = userService.createNewNote();
        InputStream text1 = new ByteArrayInputStream("dddd\nabc".getBytes());
        System.setIn(text1);
        Note note1 = userService.createNewNote();
        assertNotEquals(note.getId(), note1.getId());
    }
    @Test
    @DisplayName("вывод всех заметок")
    public void showAllNotes() {
        InputStream text = new ByteArrayInputStream("aaa\nasf asd".getBytes());
        System.setIn(text);
        Note note = userService.createNewNote();
        InputStream text1 = new ByteArrayInputStream("sssss\nabab".getBytes());
        System.setIn(text1);
        Note note1 = userService.createNewNote();

        InputStream inputStream = new ByteArrayInputStream(String.valueOf("\n ").getBytes());
        System.setIn(inputStream);
        assertEquals(7, userService.getAllNotes().size());
    }
}
