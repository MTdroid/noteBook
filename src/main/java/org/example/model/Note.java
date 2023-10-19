package org.example.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Note {
    @Getter
    private String id;
    @Getter
    private String text;
    @Getter
    private List<String> label;
    @Getter
    private static List<Note> noteList = new ArrayList<>();

    public Note(String text, List<String> label) {
        this.id = createID();
        this.text = text;
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
    // ген id
    private static AtomicLong idCounter = new AtomicLong();

    public static String createID()
    {
        return String.valueOf(idCounter.getAndIncrement());
    }
}
