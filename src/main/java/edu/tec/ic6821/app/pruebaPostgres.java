package edu.tec.ic6821.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class pruebaPostgres implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public pruebaPostgres(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        String sql = "INSERT INTO cliente VALUES (10,'andres ramirez')";
        int rows = jdbcTemplate.update(sql);
        if (rows>0){
            System.out.println("Usuario persistido coorrectamente.");
        }else{
            System.out.println("Algo se cayó :(");
        }
    }
}
