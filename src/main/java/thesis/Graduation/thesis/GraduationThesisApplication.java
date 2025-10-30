package thesis.Graduation.thesis;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJpaAuditing
public class GraduationThesisApplication {


    public static void main(String[] args) {
        SpringApplication.run(GraduationThesisApplication.class, args);
    }


}
