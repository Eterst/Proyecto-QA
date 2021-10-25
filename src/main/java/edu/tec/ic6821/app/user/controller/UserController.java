package edu.tec.ic6821.app.user.controller;

import edu.tec.ic6821.app.common.errors.ErrorDto;
import edu.tec.ic6821.app.user.model.User;
import edu.tec.ic6821.app.user.dto.InUserDto;
import edu.tec.ic6821.app.user.dto.OutUserDto;
import edu.tec.ic6821.app.user.service.UserService;
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

//@RestController
//@RequestMapping("/api/User")
public class UserController {
/*
    private final UserService userService;

    @Autowired
    public UserController(UserService UserService) { this.userService = UserService; }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody InUserDto inUserDto) {
        try {
            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Optional<User> optionalUser = userService.create(
                    inUserDto.getName(),
                    inUserDto.getText(),
                    userDetails.getUserId());

            return optionalUser.<ResponseEntity<?>>map(
                    (User) -> new ResponseEntity<>(
                            OutUserDto.from(User),
                            HttpStatus.CREATED)
            ).orElseGet(
                    () -> new ResponseEntity<>(HttpStatus.BAD_REQUEST)
            );
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorDto.from(e),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
