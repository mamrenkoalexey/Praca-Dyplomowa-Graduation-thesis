package thesis.Graduation.thesis.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import thesis.Graduation.thesis.entity.Role;
import thesis.Graduation.thesis.entity.User;
import thesis.Graduation.thesis.repository.RoleRepository;
import thesis.Graduation.thesis.repository.UserRepository;

@Configuration
public class DataInitializer {
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            Role directorRole = roleRepository.findByName("DIRECTOR")
                    .orElseGet(() -> roleRepository.save(new Role("DIRECTOR")));

            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setEnabled(true);
                admin.getRoles().add(directorRole);
                userRepository.save(admin);
                System.out.println("User created with role DIRECTOR");
            }
        };
    }
}
