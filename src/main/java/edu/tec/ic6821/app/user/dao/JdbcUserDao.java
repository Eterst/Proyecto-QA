package edu.tec.ic6821.app.user.dao;

import edu.tec.ic6821.app.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Component
public class JdbcUserDao extends JdbcDaoSupport implements UserDao {

    @Autowired
    public JdbcUserDao(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public Boolean existsById(Long userId) {
        String sql = "select count(nombre) from cliente where cedula = ?";
        Long count = getJdbcTemplate().queryForObject(sql,
                new Object[]{userId},
                Long.class);
        //Si existe, entonces solo debería de haber un nombre con ese id
        //por lo que debería retornar true
        return count == 1;
    }

    @Override
    public User create(User User) {
        String sql = "INSERT INTO cliente VALUES (?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, User.getUserId());
            ps.setString(2, User.getName());
            return ps;
        }, holder);

        return new User(User.getUserId(), User.getName());
    }

}
