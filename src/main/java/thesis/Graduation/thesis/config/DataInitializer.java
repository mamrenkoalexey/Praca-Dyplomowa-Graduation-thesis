package thesis.Graduation.thesis.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import thesis.Graduation.thesis.entity.*;
import thesis.Graduation.thesis.repository.CarRepository;
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

    @Bean
    CommandLineRunner initDatabase(CarRepository carRepository) {
        return args -> {
            if (carRepository.count() == 0) {
                carRepository.save(new Car("BMW", "X5", 2020, 35000, 60000, BodyType.SUV, EngineType.PETROL));
                carRepository.save(new Car("Audi", "A6", 2019, 28000, 80000, BodyType.SEDAN, EngineType.DIESEL));
                carRepository.save(new Car("Toyota", "Corolla", 2021, 22000, 30000, BodyType.SEDAN, EngineType.HYBRID));
                carRepository.save(new Car("Mercedes", "C-Class", 2018, 25000, 100000, BodyType.COUPE, EngineType.PETROL));
                carRepository.save(new Car("Tesla", "Model 3", 2022, 45000, 15000, BodyType.SEDAN, EngineType.ELECTRIC));
            }
        };
    }
}

