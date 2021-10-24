package edu.tec.ic6821.app.notes;

import edu.tec.ic6821.app.identity.model.CustomUserDetails;
import edu.tec.ic6821.app.identity.model.User;
import edu.tec.ic6821.app.identity.service.CustomUserDetailsService;
import edu.tec.ic6821.app.identity.service.UserService;

import edu.tec.ic6821.app.note.model.Note;
import edu.tec.ic6821.app.note.service.NoteService;
import edu.tec.ic6821.app.note.controller.NoteController;
import edu.tec.ic6821.app.identity.config.JwtAuthEntryPoint;
import edu.tec.ic6821.app.identity.config.JwtProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
public class NoteControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private NoteService noteService;

    @MockBean
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    @MockBean
    private JwtProvider jwtProvider;


    @Test
    public void givenValidNote_whenCreateNote_thenSendNote() throws Exception {
        // given
        String username = "Roy";
        String password = "vacasLocas89";

        String name = "Lista Super";
        String text = "queso, papas, cebolla";

        User user = new User(69L,username,passwordEncoder.encode(password));
        //Persisto el usuario en la base de datos
        given(userService.create(username, password)).willReturn(Optional.of(user));

        when(jwtProvider.validateJwtToken(anyString())).thenReturn(true);
        when(jwtProvider.getUsernameFromJwtToken(anyString())).thenReturn(username);
        when(customUserDetailsService.loadUserByUsername(anyString())).thenReturn(
                new CustomUserDetails(user.getId().get(), org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .authorities(Collections.emptyList())
                        .accountExpired(false)
                        .accountLocked(false)
                        .credentialsExpired(false)
                        .disabled(false)
                        .build())
        );

        Note note = new Note(0L, name, text, user.getId().get());
        given(noteService.create(name,text,user.getId().get())).willReturn(Optional.of(note));

        String body = String.format("{\"name\": \"%s\", \"text\": \"%s\"}",name, text);

        // when ... then
        mvc.perform(post("/api/note")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer sometoken")
                .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is((0))) )
                .andExpect(jsonPath("$.name", is(name)) )
                .andExpect(jsonPath("$.text").doesNotExist() )
                .andExpect(jsonPath("$.userId").doesNotExist() );
    }


}