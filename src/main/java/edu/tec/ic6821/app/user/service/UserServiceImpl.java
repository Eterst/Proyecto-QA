package edu.tec.ic6821.app.user.service;

import edu.tec.ic6821.app.user.model.User;
import edu.tec.ic6821.app.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> create(Long userId,String name) {
        Boolean userAlreadyExists = userDao.existsById(userId);
        if (userAlreadyExists) {
            User user = new User( userId,name);
            return Optional.of(userDao.create(user));
        } else {
            return Optional.empty();
        }
    }
}
