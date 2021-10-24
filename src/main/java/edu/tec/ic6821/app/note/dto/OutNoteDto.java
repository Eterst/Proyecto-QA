package edu.tec.ic6821.app.note.dto;

import edu.tec.ic6821.app.note.model.Note;


public class OutNoteDto {
    private final Long id;
    private final String name;

    public static OutNoteDto from(Note note) {
        return new OutNoteDto(note.getId(), note.getName());
    }

    public OutNoteDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }

    public String getName() { return name; }
}
