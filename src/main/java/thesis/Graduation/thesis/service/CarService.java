package thesis.Graduation.thesis.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import thesis.Graduation.thesis.entity.BodyType;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.FuelType;
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

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car updatedCar) {
        Car car = getCarById(id);
        car.setBrand(updatedCar.getBrand());
        car.setModel(updatedCar.getModel());
        car.setYear(updatedCar.getYear());
        car.setPrice(updatedCar.getPrice());
        car.setMileage(updatedCar.getMileage());
        car.setBodyType(updatedCar.getBodyType());
        car.setFuelType(updatedCar.getFuelType());
        car.setAvailable(updatedCar.isAvailable());
        car.setDescription(updatedCar.getDescription());
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }


    public List<String> getAllBrands() {
        return carRepository.findAllBrands();
    }

    public List<String> getAllModels() {
        return carRepository.findAllModels();
    }


    public List<Integer> getAllYears() {
        return carRepository.findAllYears();
    }

    public List<Car> findSearchCars(String brand,
                                    String carModel,
                                    BodyType bodyType,
                                    Double priceFrom,
                                    Double priceTo,
                                    FuelType fuelType,
                                    Integer year,
                                    Integer mileageFrom,
                                    Integer mileageTo) {

        brand = normalize(brand);
        carModel = normalize(carModel);

//        System.out.println("-> Search params: brand=" + brand + ", model=" + carModel
//                + ", body=" + bodyType + ", priceFrom=" + priceFrom + ", priceTo="
//                + priceTo + ", fuel=" + fuelType + ", year=" + year + ", mileageFrom="
//                + mileageFrom + ", mileageTo=" + mileageTo);

        Specification<Car> spec = CarSpecification.search(
                brand, carModel, bodyType, priceFrom, priceTo, fuelType, year, mileageFrom, mileageTo);

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

}
