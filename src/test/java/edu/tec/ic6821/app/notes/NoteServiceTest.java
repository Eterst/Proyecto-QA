package edu.tec.ic6821.app.notes;

import edu.tec.ic6821.app.note.dao.NoteDao;
import edu.tec.ic6821.app.note.model.Note;
import edu.tec.ic6821.app.note.service.NoteService;
import edu.tec.ic6821.app.note.service.NoteServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class NoteServiceTest {

    @TestConfiguration
    static class NoteServiceTestsConfiguration {
        @MockBean
        NoteDao noteDao;

        @Bean
        public NoteService noteService() {
            return new NoteServiceImpl(noteDao);
        }
    }
    @Autowired
    NoteService noteService;

    @Autowired
    NoteDao noteDao;

    @Before
    public void setUp() {
        Mockito.when(noteDao.existsById(0L)).thenReturn(true);
        Mockito.when(noteDao.existsById(1L)).thenReturn(false);
        Mockito.when(noteDao.create(ArgumentMatchers.any(Note.class)))
                .thenAnswer(invocation -> {
                    Note createdNote = invocation.getArgument(0);
                    return new Note(42L,createdNote.getName(), createdNote.getText(), 0L);
                });
    }

    @Test
    public void CreateNewNote_whenExistUser_thenReturnNewNote() {
        // given
        String name = "videojuegos en mi pc";
        String text = "Halo, Gta5, fallout, Fortnite, Horizon, FarCry";
        Long userId = 0L;

        // when
        Optional<Note> result = noteService.create(name, text,userId);

        // then
        assertThat(result).isNotEmpty();

    }

    @Test
    public void CreateNewNote_whenNotExistUser_thenReturnNoteEmpty() {
        // given
        String name = "Lista numeros";
        String text = "1,2,3,4,5,6,7";
        Long userId = 1L;

        // when
        Optional<Note> result = noteService.create(name, text,userId);

        // then
        assertThat(result).isEmpty();

    }
}
