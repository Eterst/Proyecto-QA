package edu.tec.ic6821.app.note.controller;

import edu.tec.ic6821.app.common.errors.ErrorDto;
import edu.tec.ic6821.app.identity.model.CustomUserDetails;
import edu.tec.ic6821.app.note.model.Note;
import edu.tec.ic6821.app.note.dto.InNoteDto;
import edu.tec.ic6821.app.note.dto.OutNoteDto;
import edu.tec.ic6821.app.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) { this.noteService = noteService; }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody InNoteDto inNoteDto) {
        try {
            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Optional<Note> optionalNote = noteService.create(
                    inNoteDto.getName(),
                    inNoteDto.getText(),
                    userDetails.getUserId());

            return optionalNote.<ResponseEntity<?>>map(
                    (note) -> new ResponseEntity<>(
                            OutNoteDto.from(note),
                            HttpStatus.CREATED)
            ).orElseGet(
                    () -> new ResponseEntity<>(HttpStatus.BAD_REQUEST)
            );
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorDto.from(e),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
