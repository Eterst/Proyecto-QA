package edu.tec.ic6821.app.note.dao;

import edu.tec.ic6821.app.note.model.Note;


public interface NoteDao {
    Note create(Note note);
    Boolean existsById(Long userId);
}
