package thesis.Graduation.thesis.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.enums.BodyType;
import thesis.Graduation.thesis.entity.enums.FuelType;
import thesis.Graduation.thesis.entity.enums.CarStatus;
import thesis.Graduation.thesis.repository.CarRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // ======= CRUD =======

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car updatedCar) {
        Car car = getCarById(id);

        car.setVin(updatedCar.getVin());
        car.setRegistrationNumber(updatedCar.getRegistrationNumber());
        car.setProductionYear(updatedCar.getProductionYear());
        car.setMileage(updatedCar.getMileage());
        car.setColor(updatedCar.getColor());
        car.setPrice(updatedCar.getPrice());
        car.setBodyType(updatedCar.getBodyType());
        car.setFuelType(updatedCar.getFuelType());
        car.setModel(updatedCar.getModel());
        car.setSalon(updatedCar.getSalon());
        car.setStatus(updatedCar.getStatus());
        car.setDescription(updatedCar.getDescription());

        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }


    public List<Car> findSearchCars(String brand,
                                    String carModel,
                                    BodyType bodyType,
                                    Double priceFrom,
                                    Double priceTo,
                                    FuelType fuelType,
                                    Integer productionYear,
                                    Double mileageFrom,
                                    Double mileageTo,
                                    CarStatus status) {

        brand = normalize(brand);
        carModel = normalize(carModel);

        Specification<Car> spec = CarSpecification.search(
                brand, carModel, bodyType, priceFrom, priceTo, fuelType,
                productionYear, mileageFrom, mileageTo, status
        );

        return carRepository.findAll(spec);
    }

    private String normalize(String value) {
        return (value == null || value.isBlank()) ? null : value;
    }


    public List<Car> randomCars(Integer count) {
        List<Car> listOfCars = getAllCars();
        Collections.shuffle(listOfCars);
        return listOfCars.stream().limit(count).toList();
    }

    public List<Car> randomCars(Integer count, Long excludeId) {
        List<Car> listOfCars = getAllCars().stream()
                .filter(c -> !c.getId().equals(excludeId))
                .collect(Collectors.toList());
        Collections.shuffle(listOfCars);
        return listOfCars.stream().limit(count).toList();
    }

    public List<String> getAllBrands() {
        return carRepository.findAll().stream()
                .map(car -> car.getModel().getBrand().getName())
                .distinct()
                .sorted()
                .toList();
    }

    public List<String> getAllModels() {
        return carRepository.findAll().stream()
                .map(car -> car.getModel().getName())
                .distinct()
                .sorted()
                .toList();
    }

    public List<Integer> getAllYears() {
        return carRepository.findAll().stream()
                .map(Car::getProductionYear)
                .distinct()
                .sorted()
                .toList();
    }
}
