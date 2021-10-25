package edu.tec.ic6821.app.user.dao;

import edu.tec.ic6821.app.user.model.User;


public interface UserDao {
    User create(User User);
    Boolean existsById(Long userId);
}
