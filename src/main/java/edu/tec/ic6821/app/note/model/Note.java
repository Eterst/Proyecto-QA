package edu.tec.ic6821.app.note.model;

import java.util.Objects;

public class Note {
    private  Long id;
    private  String name;
    private  String text;
    private  Long userId;


    public Note(Long id, String name, String text, Long userId) {
        this.id =  id;
        this.name = name;
        this.text = text;
        this.userId = userId;
    }

    public Note(String name, String text, Long userId) {
        this.name = name;
        this.text = text;
        this.userId = userId;
    }


    public Long getId() { return id; }

    public String getName() { return name; }

    public String getText() { return text; }

    public Long getUserId() { return userId; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note)) return false;
        Note note = (Note) o;
        return Objects.equals(id, note.id)
                && Objects.equals(name, note.name)
                && Objects.equals(text, note.text)
                && Objects.equals(userId, note.userId);
    }

    @Override
    public int hashCode() { return Objects.hash(id, name, text, userId); }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", userId=" + userId +
                '}';
    }
}
