package edu.tec.ic6821.app.note.dao;

import edu.tec.ic6821.app.note.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Component
public class JdbcNoteDao extends JdbcDaoSupport implements NoteDao {

    @Autowired
    public JdbcNoteDao(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public Boolean existsById(Long userId) {
        String sql = "select count(username) from user where id = ?";
        Long count = getJdbcTemplate().queryForObject(sql,
                new Object[]{userId},
                Long.class);
        //Si existe, entonces solo debería de haber un nombre con ese id
        return count == 1;
    }

    @Override
    public Note create(Note note) {
        String sql = "insert into note (name, text, userId) values (?, ?, ?)";

        KeyHolder holder = new GeneratedKeyHolder();
        getJdbcTemplate().update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, note.getName());
            ps.setString(2, note.getText());
            ps.setLong(3, note.getUserId());
            return ps;
        }, holder);

        return new Note(holder.getKey().longValue(),note.getName(),note.getText(), note.getUserId());
    }

}
