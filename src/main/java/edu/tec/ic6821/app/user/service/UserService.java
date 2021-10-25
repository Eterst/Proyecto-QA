package edu.tec.ic6821.app.user.service;

import edu.tec.ic6821.app.user.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> create(Long userId, String name);
}
