package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.example.service.UserServiceImpl.generateID;


@Getter
@Setter
public class Note {

    private int id;
    private String text;
    private List<String> label;

    @Getter
    private static List<Note> noteList = new ArrayList<>();

    public Note(String text, List<String> label) {
        this.id = generateID();
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
        String joinLabels = String
                .join("}; ", label)
                .replaceAll(" ", "{");
        return "{" + id + "} # {" + text + "}\n{" + joinLabels + "}\n \n=================== \n";

    }


}
