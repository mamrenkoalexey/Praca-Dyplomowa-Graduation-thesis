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
                carRepository.save(new Car("BMW", "X5", 2020, 35000, 60000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("Audi", "A6", 2019, 28000, 80000, BodyType.SEDAN, FuelType.DIESEL));
                carRepository.save(new Car("Toyota", "Corolla", 2021, 22000, 30000, BodyType.SEDAN, FuelType.HYBRID));
                carRepository.save(new Car("Mercedes", "C-Class", 2018, 25000, 100000, BodyType.COUPE, FuelType.PETROL));
                carRepository.save(new Car("Tesla", "Model 3", 2022, 45000, 15000, BodyType.SEDAN, FuelType.ELECTRIC));
                carRepository.save(new Car("Volkswagen", "Tiguan", 2020, 27000, 50000, BodyType.SUV, FuelType.DIESEL));
                carRepository.save(new Car("Ford", "Focus", 2019, 18000, 70000, BodyType.HATCHBACK, FuelType.PETROL));
                carRepository.save(new Car("Chevrolet", "Camaro", 2021, 40000, 20000, BodyType.COUPE, FuelType.PETROL));
                carRepository.save(new Car("Nissan", "Qashqai", 2020, 23000, 45000, BodyType.SUV, FuelType.HYBRID));
                carRepository.save(new Car("Hyundai", "Tucson", 2022, 26000, 10000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("Kia", "Sportage", 2021, 25000, 20000, BodyType.SUV, FuelType.DIESEL));
                carRepository.save(new Car("Mazda", "CX-5", 2019, 24000, 60000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("Honda", "Civic", 2020, 21000, 35000, BodyType.SEDAN, FuelType.HYBRID));
                carRepository.save(new Car("Volvo", "XC60", 2021, 42000, 25000, BodyType.SUV, FuelType.HYBRID));
                carRepository.save(new Car("Peugeot", "3008", 2020, 27000, 40000, BodyType.SUV, FuelType.DIESEL));
                carRepository.save(new Car("Renault", "Megane", 2018, 16000, 90000, BodyType.HATCHBACK, FuelType.PETROL));
                carRepository.save(new Car("Skoda", "Octavia", 2021, 23000, 30000, BodyType.SEDAN, FuelType.DIESEL));
                carRepository.save(new Car("Subaru", "Outback", 2019, 29000, 70000, BodyType.WAGON, FuelType.PETROL));
                carRepository.save(new Car("Jeep", "Compass", 2021, 32000, 25000, BodyType.SUV, FuelType.DIESEL));
                carRepository.save(new Car("Porsche", "Macan", 2022, 65000, 12000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("Lexus", "RX 450h", 2021, 55000, 18000, BodyType.SUV, FuelType.HYBRID));
                carRepository.save(new Car("Ferrari", "Portofino", 2020, 200000, 8000, BodyType.COUPE, FuelType.PETROL));
                carRepository.save(new Car("Opel", "Astra", 2019, 17000, 75000, BodyType.HATCHBACK, FuelType.DIESEL));
                carRepository.save(new Car("Mitsubishi", "Outlander", 2020, 28000, 50000, BodyType.SUV, FuelType.HYBRID));
                carRepository.save(new Car("Jaguar", "I-PACE", 2022, 70000, 10000, BodyType.SUV, FuelType.ELECTRIC));
                carRepository.save(new Car("Volkswagen", "Passat", 2019, 24000, 70000, BodyType.SEDAN, FuelType.DIESEL));
                carRepository.save(new Car("Ford", "Focus", 2020, 18000, 40000, BodyType.HATCHBACK, FuelType.PETROL));
                carRepository.save(new Car("Chevrolet", "Malibu", 2021, 25000, 30000, BodyType.SEDAN, FuelType.PETROL));
                carRepository.save(new Car("Nissan", "Rogue", 2022, 28000, 20000, BodyType.SUV, FuelType.HYBRID));
                carRepository.save(new Car("Hyundai", "Elantra", 2018, 17000, 85000, BodyType.SEDAN, FuelType.PETROL));
                carRepository.save(new Car("Kia", "Sorento", 2021, 31000, 25000, BodyType.SUV, FuelType.DIESEL));
                carRepository.save(new Car("Mazda", "CX-5", 2020, 27000, 40000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("Honda", "Accord", 2019, 23000, 65000, BodyType.SEDAN, FuelType.HYBRID));
                carRepository.save(new Car("Volvo", "XC60", 2021, 43000, 15000, BodyType.SUV, FuelType.HYBRID));
                carRepository.save(new Car("Peugeot", "508", 2020, 29000, 35000, BodyType.SEDAN, FuelType.DIESEL));
                carRepository.save(new Car("Renault", "Captur", 2022, 21000, 10000, BodyType.CROSSOVER, FuelType.PETROL));
                carRepository.save(new Car("Skoda", "Kodiaq", 2021, 33000, 22000, BodyType.SUV, FuelType.DIESEL));
                carRepository.save(new Car("Subaru", "Forester", 2020, 29000, 40000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("Jeep", "Cherokee", 2021, 35000, 30000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("Porsche", "Cayenne", 2022, 80000, 12000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("Lexus", "NX 300h", 2021, 50000, 18000, BodyType.SUV, FuelType.HYBRID));
                carRepository.save(new Car("Ferrari", "Roma", 2020, 210000, 5000, BodyType.COUPE, FuelType.PETROL));
                carRepository.save(new Car("Opel", "Insignia", 2019, 19000, 75000, BodyType.SEDAN, FuelType.DIESEL));
                carRepository.save(new Car("Mitsubishi", "Outlander", 2020, 28000, 45000, BodyType.SUV, FuelType.HYBRID));
                carRepository.save(new Car("Jaguar", "XF", 2021, 56000, 15000, BodyType.SEDAN, FuelType.PETROL));
                carRepository.save(new Car("Fiat", "Tipo", 2018, 15000, 95000, BodyType.SEDAN, FuelType.DIESEL));
                carRepository.save(new Car("Citroen", "C5 Aircross", 2021, 27000, 30000, BodyType.SUV, FuelType.DIESEL));
                carRepository.save(new Car("Suzuki", "Vitara", 2020, 22000, 40000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("Land Rover", "Discovery", 2021, 70000, 25000, BodyType.SUV, FuelType.DIESEL));
                carRepository.save(new Car("Seat", "Ateca", 2020, 26000, 35000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("Mini", "Countryman", 2021, 34000, 20000, BodyType.CROSSOVER, FuelType.HYBRID));
                carRepository.save(new Car("Chery", "Tiggo 7 Pro", 2022, 22000, 10000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("Geely", "Coolray", 2021, 21000, 15000, BodyType.CROSSOVER, FuelType.PETROL));
                carRepository.save(new Car("Haval", "Jolion", 2022, 23000, 12000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("Infiniti", "QX50", 2021, 46000, 18000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("Alfa Romeo", "Giulia", 2020, 42000, 30000, BodyType.SEDAN, FuelType.PETROL));
                carRepository.save(new Car("Cadillac", "XT5", 2021, 55000, 25000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("Dodge", "Charger", 2019, 37000, 60000, BodyType.SEDAN, FuelType.PETROL));
                carRepository.save(new Car("Lincoln", "Corsair", 2022, 52000, 10000, BodyType.SUV, FuelType.HYBRID));
                carRepository.save(new Car("Chrysler", "300", 2020, 35000, 40000, BodyType.SEDAN, FuelType.PETROL));
                carRepository.save(new Car("Acura", "RDX", 2021, 49000, 20000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("Buick", "Enclave", 2020, 44000, 30000, BodyType.SUV, FuelType.PETROL));
                carRepository.save(new Car("GMC", "Terrain", 2019, 33000, 50000, BodyType.SUV, FuelType.DIESEL));
                carRepository.save(new Car("Genesis", "G80", 2022, 60000, 10000, BodyType.SEDAN, FuelType.HYBRID));
                carRepository.save(new Car("Peugeot", "3008", 2019, 26000, 60000, BodyType.SUV, FuelType.DIESEL));
                carRepository.save(new Car("BYD", "Tang", 2022, 38000, 8000, BodyType.SUV, FuelType.ELECTRIC));
                carRepository.save(new Car("Lucid", "Air", 2023, 95000, 5000, BodyType.SEDAN, FuelType.ELECTRIC));
                carRepository.save(new Car("Polestar", "2", 2022, 58000, 12000, BodyType.SEDAN, FuelType.ELECTRIC));
                carRepository.save(new Car("Rivian", "R1S", 2023, 90000, 7000, BodyType.SUV, FuelType.ELECTRIC));
                carRepository.save(new Car("Toyota", "RAV4", 2020, 27000, 45000, BodyType.SUV, FuelType.HYBRID));
                carRepository.save(new Car("Audi", "Q5", 2021, 48000, 20000, BodyType.SUV, FuelType.DIESEL));
                carRepository.save(new Car("BMW", "3 Series", 2019, 30000, 65000, BodyType.SEDAN, FuelType.PETROL));
                carRepository.save(new Car("Mercedes", "GLC", 2021, 52000, 18000, BodyType.SUV, FuelType.HYBRID));
                carRepository.save(new Car("Tesla", "Model Y", 2022, 60000, 10000, BodyType.SUV, FuelType.ELECTRIC));
                carRepository.save(new Car("Volkswagen", "Golf", 2020, 20000, 50000, BodyType.HATCHBACK, FuelType.PETROL));
                carRepository.save(new Car("Honda", "CR-V", 2021, 32000, 25000, BodyType.SUV, FuelType.HYBRID));

            }
        };
    }
}

