package edu.tec.ic6821.app.notes;

import edu.tec.ic6821.app.identity.dao.UserDao;
import edu.tec.ic6821.app.identity.model.User;

import edu.tec.ic6821.app.note.dao.NoteDao;
import edu.tec.ic6821.app.note.model.Note;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteDaoTests {

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    public void givenNote_whenCreate_thenReturnNoteWithGeneratedId() {
        // given
        //Mockito.when(noteDao.existsById(0L)).thenReturn(true);

        User user = new User("Roy", passwordEncoder.encode("postedeluz79"));
        User persistedUser = userDao.create(user);

        Note note = new Note("Planetas","Saturno, Tierra, Neptuno, Urano",persistedUser.getId().get());

        // when
        Note createdNote = noteDao.create(note);

        // then
        assertThat(createdNote.getId()).isNotNull();
        assertThat(createdNote.getName()).isEqualTo(note.getName());
        assertThat(createdNote.getText()).isEqualTo(note.getText());
        assertThat(createdNote.getUserId()).isEqualTo(note.getUserId());
    }
}

