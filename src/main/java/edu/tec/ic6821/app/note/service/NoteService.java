package edu.tec.ic6821.app.note.service;

import edu.tec.ic6821.app.note.model.Note;

import java.util.Optional;

public interface NoteService {
    Optional<Note> create(String name, String text, Long userId);
}
