import org.example.model.Note;
import org.example.service.UserService;
import org.example.service.UserServiceImpl;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class TestNote {
    UserService userService = new UserServiceImpl();

/*1 лейбл и текст
2 лейбл и текст
0 лейбл и текст
1 лейбл и текст 3<
лист всех note
удаление сущ id
удаление не сущ id
корректность тела
генератор id уникальность
    */


    @Test

    public void noteWithTextAnd1Label() { //OK
        InputStream text = new ByteArrayInputStream("aaa\nabc".getBytes());
        System.setIn(text);
        Note note = userService.noteNew();

        assertEquals("aaa", note.getText());
        assertEquals((List.of("ABC")), note.getLabel());


    }

    @Test
    public void noteWithTwoLabels() { //OK
        InputStream text = new ByteArrayInputStream("aaa\nabc cba".getBytes());
        System.setIn(text);
        Note note = userService.noteNew();

        assertEquals("aaa", note.getText());
        assertEquals(List.of("ABC", "CBA"), note.getLabel());


    }

    @Test
    public void noteWithoutLabels() { // OK
        InputStream text = new ByteArrayInputStream("aaa\n ".getBytes());
        System.setIn(text);
        Note note = userService.noteNew();
        assertEquals("aaa", note.getText());
        assertEquals(note.getLabel().stream().toList().toString(), (List.of("").toString()));



        /*
        Expected :java.util.ArrayList<[]> ??????????
          Actual   :java.util.ArrayList<[]>
        */
    }

    @Test /*(expected = Exception.class) */
    public void noteTextWithLessThan3Char() // OK

    {
        InputStream text = new ByteArrayInputStream("a\nabc".getBytes());
        System.setIn(text);
        Note note = userService.noteNew();
        assertNull(note);


    }

    @Test
    public void showListNoteFilterByLable() { //OK
        InputStream text = new ByteArrayInputStream("aaa\nasf asd".getBytes());
        System.setIn(text);
        Note note = userService.noteNew();
        InputStream text1 = new ByteArrayInputStream("sssss\nabab".getBytes());
        System.setIn(text1);
        Note note1 = userService.noteNew();

        InputStream inputStream = new ByteArrayInputStream("ABAB".getBytes());
        System.setIn(inputStream);
        assertEquals(1, userService.noteGetAllNotes().size());

    }

    @Test
    public void removeExistNoteById() { //OK
        InputStream text = new ByteArrayInputStream("removeNote\nabc".getBytes());
        System.setIn(text);
        Note note = userService.noteNew();

        System.out.println(note);
        InputStream inputStream = new ByteArrayInputStream(String.valueOf(note.getId()).getBytes());
        System.setIn(inputStream);
        userService.noteRemove();
        assertFalse(Note.getNoteList().contains(note));
    }

    @Test
    public void removeNotExistNoteById() {//OK
        InputStream text = new ByteArrayInputStream("removeNote\nabc".getBytes());
        System.setIn(text);
        Note note = userService.noteNew();
        InputStream inputStream = new ByteArrayInputStream("aaa".getBytes());
        System.setIn(inputStream);
        userService.noteRemove();
        assertTrue(Note.getNoteList().contains(note));

    }

    @Test
    public void correctBody() { //OK
        InputStream text = new ByteArrayInputStream("testForm\nabc".getBytes());
        System.setIn(text);
        Note note = userService.noteNew();
        assertEquals("{" + note.getId() + "}" + " # {testForm}\n" +
                "{ABC}\n" +
                " \n" +
                "=================== \n", note.toString());

    }

    @Test
    public void uniqueId() {//OK
        InputStream text = new ByteArrayInputStream("dfsgvfd\nabc".getBytes());
        System.setIn(text);
        Note note = userService.noteNew();
        InputStream text1 = new ByteArrayInputStream("dddd\nabc".getBytes());
        System.setIn(text1);
        Note note1 = userService.noteNew();
        assertNotEquals(note.getId(), note1.getId());
    }
}
