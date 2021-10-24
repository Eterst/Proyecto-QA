package edu.tec.ic6821.app.note.service;

import edu.tec.ic6821.app.note.model.Note;
import edu.tec.ic6821.app.note.dao.NoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteDao noteDao;

    @Autowired
    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public Optional<Note> create(String name, String text, Long userId) {
        Boolean userAlreadyExists = noteDao.existsById(userId);
        if (userAlreadyExists) {
            Note note = new Note(name, text, userId);
            return Optional.of(noteDao.create(note));
        } else {
            return Optional.empty();
        }
    }
}
