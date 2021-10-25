package edu.tec.ic6821.app.users;

import edu.tec.ic6821.app.user.dao.UserDao;
import edu.tec.ic6821.app.user.model.User;
import edu.tec.ic6821.app.user.service.UserService;
import edu.tec.ic6821.app.user.service.UserServiceImpl;

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
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceTestsConfiguration {
        @MockBean
        UserDao userDao;

        @Bean
        public UserService UserService() {
            return new UserServiceImpl(userDao);
        }
    }
    @Autowired
    UserService userService;

    @Autowired
    UserDao UserDao;

    @Before
    public void setUp() {
        //Este digamos que existe
        Mockito.when(UserDao.existsById(20L)).thenReturn(true);
        //Este no existe
        Mockito.when(UserDao.existsById(21L)).thenReturn(false);

        Mockito.when(UserDao.create(ArgumentMatchers.any(User.class)))
                .thenAnswer(invocation -> {
                    User createdUser = invocation.getArgument(0);
                    return new User(42L,createdUser.getName());
                });
    }

    @Test
    public void CreateNewUser_whenNotExistUser_thenReturnNewUser() {
        // given
        Long userId = 20L;
        String name = "David";

        // when
        Optional<User> result = userService.create(userId, name);

        // then
        assertThat(result).isNotEmpty();

    }

    @Test
    public void CreateNewUser_whenUserExist_thenReturnUserEmpty() {
        // given
        Long userId = 21L;
        String name = "Carlos";

        // when
        Optional<User> result = userService.create(userId, name);

        // then
        assertThat(result).isEmpty();

    }
}
