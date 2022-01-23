package ru.DiaSoft.testUserControl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.DiaSoft.testUserControl.models.User;

import java.util.List;
import java.util.UUID;

@Component
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> userList() {

        String SQL = "SELECT * FROM tuser";

        return jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(User.class));
    }

    public User getUser(int id) {

        String SQL = "SELECT * FROM tuser WHERE userid=?";

        return jdbcTemplate.query(SQL, new Object[]{id}, new BeanPropertyRowMapper<>(User.class)).stream().findAny().orElse(null);
    }

    public void save(User user) {

        String SQL = "INSERT INTO tuser(name, pass, mail) VALUES(?,?,?)";

        jdbcTemplate.update(SQL,
                user.getName(),
                user.getPass(),
                user.getMail());

    }

    public void update(User user, int id){

        String SQL = "UPDATE tuser SET name=?, pass=?, mail=? WHERE userid = ?";

        jdbcTemplate.update(SQL,
                user.getName(),
                user.getPass(),
                user.getMail(),
                id);
    }

    public void delete(int id){

        String SQL = "DELETE FROM tuser WHERE userid = ?";
        jdbcTemplate.update(SQL, id);
    }
}
