package thesis.Graduation.thesis.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import thesis.Graduation.thesis.entity.*;
import thesis.Graduation.thesis.repository.CarRepository;
import thesis.Graduation.thesis.repository.DealershipRepository;
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
    CommandLineRunner initDatabase(CarRepository carRepository, DealershipRepository dealershipRepository) {
        return args -> {
            if (carRepository.count() == 0) {
                carRepository.save(new Car("BMW", "X5", 2020, 35000, 60000, BodyType.SUV, FuelType.PETROL,
                        "Luksusowy SUV klasy premium z napędem 4x4, idealny do długich podróży i miejskiej elegancji."));

                carRepository.save(new Car("Audi", "A6", 2019, 28000, 80000, BodyType.SEDAN, FuelType.DIESEL,
                        "Stylowy sedan o doskonałej jakości wykonania i mocnym silniku Diesla — komfort i prestiż w jednym."));

                carRepository.save(new Car("Toyota", "Corolla", 2021, 22000, 30000, BodyType.SEDAN, FuelType.HYBRID,
                        "Niezawodny hybrydowy sedan, oszczędny i przyjazny środowisku, idealny do codziennego użytku."));

                carRepository.save(new Car("Mercedes", "C-Class", 2018, 25000, 100000, BodyType.COUPE, FuelType.PETROL,
                        "Klasyczne coupe z elegancką sylwetką i doskonałym prowadzeniem, typowy luksus Mercedesa."));

                carRepository.save(new Car("Tesla", "Model 3", 2022, 45000, 15000, BodyType.SEDAN, FuelType.ELECTRIC,
                        "Elektryczny sedan o futurystycznym wnętrzu i zasięgu do 500 km, wyposażony w autopilota."));

                carRepository.save(new Car("Volkswagen", "Tiguan", 2020, 27000, 50000, BodyType.SUV, FuelType.DIESEL,
                        "Wszechstronny SUV rodzinny z przestronnym wnętrzem i nowoczesnymi systemami bezpieczeństwa."));

                carRepository.save(new Car("Ford", "Focus", 2019, 18000, 70000, BodyType.HATCHBACK, FuelType.PETROL,
                        "Zwinny hatchback z dynamicznym prowadzeniem i solidnym wyposażeniem technologicznym."));

                carRepository.save(new Car("Chevrolet", "Camaro", 2021, 40000, 20000, BodyType.COUPE, FuelType.PETROL,
                        "Ikoniczny amerykański muscle car o potężnym silniku V8 i agresywnym designie."));

                carRepository.save(new Car("Nissan", "Qashqai", 2020, 23000, 45000, BodyType.SUV, FuelType.HYBRID,
                        "Popularny crossover hybrydowy z nowoczesnym designem i wysokim komfortem jazdy."));

                carRepository.save(new Car("Hyundai", "Tucson", 2022, 26000, 10000, BodyType.SUV, FuelType.PETROL,
                        "Nowoczesny SUV z futurystycznym wyglądem, dużą ilością systemów bezpieczeństwa i komfortowym wnętrzem."));

                carRepository.save(new Car("Kia", "Sportage", 2021, 25000, 20000, BodyType.SUV, FuelType.DIESEL,
                        "Praktyczny SUV o nowoczesnym designie i niskim zużyciu paliwa, idealny na rodzinne wyprawy."));

                carRepository.save(new Car("Mazda", "CX-5", 2019, 24000, 60000, BodyType.SUV, FuelType.PETROL,
                        "Stylowy SUV z dynamicznym prowadzeniem i dopracowanym wnętrzem klasy premium."));

                carRepository.save(new Car("Honda", "Civic", 2020, 21000, 35000, BodyType.SEDAN, FuelType.HYBRID,
                        "Ekonomiczny i niezawodny sedan hybrydowy, który łączy styl z praktycznością."));

                carRepository.save(new Car("Volvo", "XC60", 2021, 42000, 25000, BodyType.SUV, FuelType.HYBRID,
                        "Bezpieczny i elegancki SUV z napędem hybrydowym i skandynawskim minimalizmem w środku."));

                carRepository.save(new Car("Peugeot", "3008", 2020, 27000, 40000, BodyType.SUV, FuelType.DIESEL,
                        "Francuski SUV o futurystycznym wnętrzu i oszczędnym silniku Diesla, idealny do miasta i podróży."));

                carRepository.save(new Car("Renault", "Megane", 2018, 16000, 90000, BodyType.HATCHBACK, FuelType.PETROL,
                        "Stylowy hatchback z dynamicznym silnikiem i komfortowym zawieszeniem."));

                carRepository.save(new Car("Skoda", "Octavia", 2021, 23000, 30000, BodyType.SEDAN, FuelType.DIESEL,
                        "Przestronny sedan z niezawodnym silnikiem i doskonałą ekonomią paliwa."));

                carRepository.save(new Car("Subaru", "Outback", 2019, 29000, 70000, BodyType.WAGON, FuelType.PETROL,
                        "Uniwersalny samochód terenowo-rodzinny z napędem 4x4 i wysokim prześwitem."));

                carRepository.save(new Car("Jeep", "Compass", 2021, 32000, 25000, BodyType.SUV, FuelType.DIESEL,
                        "Kompaktowy SUV z terenowym charakterem i solidnym napędem na wszystkie koła."));

                carRepository.save(new Car("Porsche", "Macan", 2022, 65000, 12000, BodyType.SUV, FuelType.PETROL,
                        "Sportowy SUV klasy premium o imponujących osiągach i luksusowym wnętrzu."));

                carRepository.save(new Car("Lexus", "RX 450h", 2021, 55000, 18000, BodyType.SUV, FuelType.HYBRID,
                        "Hybrydowy SUV łączący luksus, komfort i wyjątkową kulturę pracy silnika."));

                carRepository.save(new Car("Ferrari", "Portofino", 2020, 200000, 8000, BodyType.COUPE, FuelType.PETROL,
                        "Ekskluzywne sportowe coupe z potężnym V8 i zapierającym dech wyglądem."));

                carRepository.save(new Car("Opel", "Astra", 2019, 17000, 75000, BodyType.HATCHBACK, FuelType.DIESEL,
                        "Niemiecki hatchback o dobrych osiągach, przestronnym wnętrzu i niskich kosztach eksploatacji."));

                carRepository.save(new Car("Mitsubishi", "Outlander", 2020, 28000, 50000, BodyType.SUV, FuelType.HYBRID,
                        "Solidny SUV hybrydowy z napędem 4x4 i dużą przestrzenią dla całej rodziny."));

                carRepository.save(new Car("Jaguar", "I-PACE", 2022, 70000, 10000, BodyType.SUV, FuelType.ELECTRIC,
                        "Luksusowy elektryczny SUV z imponującym momentem obrotowym i eleganckim wnętrzem."));

                carRepository.save(new Car("Volkswagen", "Passat", 2019, 24000, 70000, BodyType.SEDAN, FuelType.DIESEL,
                        "Klasyczny niemiecki sedan o świetnej jakości wykonania i niskim spalaniu."));

                carRepository.save(new Car("Ford", "Focus", 2020, 18000, 40000, BodyType.HATCHBACK, FuelType.PETROL,
                        "Zbalansowany hatchback z doskonałym zawieszeniem i ekonomicznym silnikiem."));

                carRepository.save(new Car("Chevrolet", "Malibu", 2021, 25000, 30000, BodyType.SEDAN, FuelType.PETROL,
                        "Komfortowy sedan amerykański o eleganckim wyglądzie i cichej kabinie."));

                carRepository.save(new Car("Nissan", "Rogue", 2022, 28000, 20000, BodyType.SUV, FuelType.HYBRID,
                        "Nowoczesny SUV z napędem hybrydowym i zaawansowanymi systemami wspomagania kierowcy."));

                carRepository.save(new Car("Hyundai", "Elantra", 2018, 17000, 85000, BodyType.SEDAN, FuelType.PETROL,
                        "Praktyczny sedan z dynamicznym silnikiem i wysokim poziomem niezawodności."));

                carRepository.save(new Car("Kia", "Sorento", 2021, 31000, 25000, BodyType.SUV, FuelType.DIESEL,
                        "Duży SUV rodzinny z nowoczesnym wnętrzem i oszczędnym silnikiem Diesla."));

                carRepository.save(new Car("Mazda", "CX-5", 2020, 27000, 40000, BodyType.SUV, FuelType.PETROL,
                        "Zrównoważony SUV z doskonałym prowadzeniem i eleganckim wnętrzem."));

                carRepository.save(new Car("Honda", "Accord", 2019, 23000, 65000, BodyType.SEDAN, FuelType.HYBRID,
                        "Hybrydowy sedan łączący komfort, niezawodność i niskie spalanie."));

                carRepository.save(new Car("Volvo", "XC60", 2021, 43000, 15000, BodyType.SUV, FuelType.HYBRID,
                        "Bezpieczny SUV z nowoczesną technologią i luksusowym wnętrzem."));

                carRepository.save(new Car("Peugeot", "508", 2020, 29000, 35000, BodyType.SEDAN, FuelType.DIESEL,
                        "Elegancki sedan z francuskim charakterem i mocnym silnikiem Diesla."));

                carRepository.save(new Car("Renault", "Captur", 2022, 21000, 10000, BodyType.CROSSOVER, FuelType.PETROL,
                        "Kompaktowy crossover idealny do miasta, z modnym wnętrzem i niskim spalaniem."));

                carRepository.save(new Car("Skoda", "Kodiaq", 2021, 33000, 22000, BodyType.SUV, FuelType.DIESEL,
                        "Przestronny SUV rodzinny z dużym bagażnikiem i ekonomicznym napędem."));

                carRepository.save(new Car("Subaru", "Forester", 2020, 29000, 40000, BodyType.SUV, FuelType.PETROL,
                        "Trwały SUV z napędem 4x4 i wysoką niezawodnością, gotowy na każdą trasę."));

                carRepository.save(new Car("Jeep", "Cherokee", 2021, 35000, 30000, BodyType.SUV, FuelType.PETROL,
                        "Amerykański SUV z napędem 4x4 i klasycznym terenowym charakterem."));

                carRepository.save(new Car("Porsche", "Cayenne", 2022, 80000, 12000, BodyType.SUV, FuelType.PETROL,
                        "Sportowy SUV z osiągami auta wyścigowego i komfortem klasy luksusowej."));

                carRepository.save(new Car("Lexus", "NX 300h", 2021, 50000, 18000, BodyType.SUV, FuelType.HYBRID,
                        "Hybrydowy SUV o wyjątkowej kulturze pracy silnika i wysokim poziomie komfortu."));

                carRepository.save(new Car("Ferrari", "Roma", 2020, 210000, 5000, BodyType.COUPE, FuelType.PETROL,
                        "Nowoczesne coupe o zachwycającym designie i osiągach godnych toru wyścigowego."));

                carRepository.save(new Car("Opel", "Insignia", 2019, 19000, 75000, BodyType.SEDAN, FuelType.DIESEL,
                        "Wygodny sedan klasy średniej z przestronnym wnętrzem i solidnym napędem Diesla."));

                carRepository.save(new Car("Mitsubishi", "Outlander", 2020, 28000, 45000, BodyType.SUV, FuelType.HYBRID,
                        "Praktyczny SUV hybrydowy z napędem 4x4, idealny na długie podróże rodzinne."));

                carRepository.save(new Car("Jaguar", "XF", 2021, 56000, 15000, BodyType.SEDAN, FuelType.PETROL,
                        "Luksusowy sedan o sportowym charakterze i wyrafinowanym brytyjskim stylu."));

                carRepository.save(new Car("Fiat", "Tipo", 2018, 15000, 95000, BodyType.SEDAN, FuelType.DIESEL,
                        "Ekonomiczny sedan o prostym designie i niskich kosztach utrzymania."));

                carRepository.save(new Car("Citroen", "C5 Aircross", 2021, 27000, 30000, BodyType.SUV, FuelType.DIESEL,
                        "Komfortowy SUV z zawieszeniem typu Comfort i nowoczesnym wnętrzem."));

                carRepository.save(new Car("Suzuki", "Vitara", 2020, 22000, 40000, BodyType.SUV, FuelType.PETROL,
                        "Lekki SUV z napędem 4x4 i oszczędnym silnikiem benzynowym."));

                carRepository.save(new Car("Land Rover", "Discovery", 2021, 70000, 25000, BodyType.SUV, FuelType.DIESEL,
                        "Luksusowy SUV terenowy o legendarnych możliwościach jazdy w trudnym terenie."));

                carRepository.save(new Car("Seat", "Ateca", 2020, 26000, 35000, BodyType.SUV, FuelType.PETROL,
                        "Dynamiczny SUV o sportowym prowadzeniu i nowoczesnym wnętrzu."));

                carRepository.save(new Car("Mini", "Countryman", 2021, 34000, 20000, BodyType.CROSSOVER, FuelType.HYBRID,
                        "Stylowy crossover z napędem hybrydowym i charakterystycznym designem Mini."));

                carRepository.save(new Car("Chery", "Tiggo 7 Pro", 2022, 22000, 10000, BodyType.SUV, FuelType.PETROL,
                        "Nowoczesny chiński SUV z bogatym wyposażeniem i eleganckim wnętrzem."));

                carRepository.save(new Car("Geely", "Coolray", 2021, 21000, 15000, BodyType.CROSSOVER, FuelType.PETROL,
                        "Kompaktowy crossover o sportowym wyglądzie i nowoczesnych technologiach."));

                carRepository.save(new Car("Haval", "Jolion", 2022, 23000, 12000, BodyType.SUV, FuelType.PETROL,
                        "Nowoczesny SUV o odważnym designie i dobrym stosunku ceny do jakości."));

                carRepository.save(new Car("Infiniti", "QX50", 2021, 46000, 18000, BodyType.SUV, FuelType.PETROL,
                        "Ekskluzywny SUV klasy premium o wyjątkowym komforcie jazdy i elegancji."));

                carRepository.save(new Car("Alfa Romeo", "Giulia", 2020, 42000, 30000, BodyType.SEDAN, FuelType.PETROL,
                        "Stylowy włoski sedan o sportowym temperamencie i precyzyjnym prowadzeniu."));

                carRepository.save(new Car("Cadillac", "XT5", 2021, 55000, 25000, BodyType.SUV, FuelType.PETROL,
                        "Amerykański luksusowy SUV o przestronnym wnętrzu i wyjątkowym komforcie."));

                carRepository.save(new Car("Dodge", "Charger", 2019, 37000, 60000, BodyType.SEDAN, FuelType.PETROL,
                        "Mocny sedan z duszą muscle cara i klasycznym amerykańskim charakterem."));

                carRepository.save(new Car("Lincoln", "Corsair", 2022, 52000, 10000, BodyType.SUV, FuelType.HYBRID,
                        "Ekskluzywny SUV hybrydowy z cichym wnętrzem i luksusowym wykończeniem."));

                carRepository.save(new Car("Chrysler", "300", 2020, 35000, 40000, BodyType.SEDAN, FuelType.PETROL,
                        "Duży amerykański sedan o klasycznym designie i potężnym silniku."));

                carRepository.save(new Car("Acura", "RDX", 2021, 49000, 20000, BodyType.SUV, FuelType.PETROL,
                        "Sportowy SUV premium z napędem AWD i zaawansowanymi systemami bezpieczeństwa."));

                carRepository.save(new Car("Buick", "Enclave", 2020, 44000, 30000, BodyType.SUV, FuelType.PETROL,
                        "Komfortowy amerykański SUV rodzinny z eleganckim wnętrzem."));

                carRepository.save(new Car("GMC", "Terrain", 2019, 33000, 50000, BodyType.SUV, FuelType.DIESEL,
                        "Solidny SUV z napędem 4x4 i trwałym silnikiem Diesla."));

                carRepository.save(new Car("Genesis", "G80", 2022, 60000, 10000, BodyType.SEDAN, FuelType.HYBRID,
                        "Luksusowy sedan klasy premium łączący elegancję z nowoczesną technologią."));

                carRepository.save(new Car("BYD", "Tang", 2022, 45000, 12000, BodyType.SUV, FuelType.ELECTRIC,
                        "Elektryczny SUV o dużym zasięgu i nowoczesnym, komfortowym wnętrzu."));

            }
            if (dealershipRepository.count() == 0) {
                dealershipRepository.save(new Dealership("Warszawa FBC", "Warszawa", "ul. Marszałkowska 123", "+48 500 123 456", "warszaw@fastbuycar.pl"));
                dealershipRepository.save(new Dealership("Krakow FBC", "Kraków", "ul. Długa 45", "+48 501 987 654", "krakow@fastbuycar.pl"));


            }
        };
    }
}
