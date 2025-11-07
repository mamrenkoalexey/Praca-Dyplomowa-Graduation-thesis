package thesis.Graduation.thesis.service;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import thesis.Graduation.thesis.entity.Brand;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.Model;
import thesis.Graduation.thesis.entity.enums.BodyType;
import thesis.Graduation.thesis.entity.enums.CarStatus;
import thesis.Graduation.thesis.entity.enums.FuelType;
import thesis.Graduation.thesis.repository.BrandRepository;
import thesis.Graduation.thesis.repository.CarRepository;
import thesis.Graduation.thesis.repository.ModelRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository repository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    public CarService(CarRepository repository, BrandRepository brandRepository, ModelRepository modelRepository) {
        this.repository = repository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    public List<Car> getAllCars() {
        return repository.findAll();
    }

    public List<Car> getAllAvailableCars() {
        return repository.findByStatus(CarStatus.AVAILABLE);
    }


    public Car getCarById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id " + id));
    }

    public Car saveCar(Car car) {
        return repository.save(car);
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

        return repository.save(car);
    }

    public void deleteCar(Long id) {
        repository.deleteById(id);
    }

    public List<Car> findSearchCars(Brand brand,
                                    Model carModel,
                                    BodyType bodyType,
                                    Double priceFrom,
                                    Double priceTo,
                                    FuelType fuelType,
                                    Integer productionYear,
                                    Double mileageFrom,
                                    Double mileageTo) {

        Specification<Car> spec = CarSpecification.search(
                brand, carModel, bodyType, priceFrom, priceTo, fuelType,
                productionYear, mileageFrom, mileageTo
        );
        return repository.findAll(spec);
    }

    public List<Car> randomCars(Integer count) {
        List<Car> listOfCars = getAllAvailableCars();
        Collections.shuffle(listOfCars);
        return listOfCars.stream().limit(count).toList();
    }

    public List<Car> randomCars(Integer count, Long excludeId) {
        List<Car> listOfCars = getAllAvailableCars().stream()
                .filter(c -> !c.getId().equals(excludeId))
                .collect(Collectors.toList());
        Collections.shuffle(listOfCars);
        return listOfCars.stream().limit(count).toList();
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll(Sort.by("name").ascending());
    }

    public List<Model> getAllModels() {
        return modelRepository.findAll(Sort.by("name").ascending());
    }

    public List<Integer> getAllProductionYear() {
        return repository.findAllProductionYear();
    }

    public List<Model> getModelsByBrand(Long brand) {
        return repository.findModelsByBrand(brand);
    }

    public boolean searchNullParam(Brand brand,
                                   Model carModel,
                                   BodyType bodyType,
                                   Double priceFrom,
                                   Double priceTo,
                                   FuelType fuelType,
                                   Integer productionYear,
                                   Double mileageFrom,
                                   Double mileageTo) {

        boolean result = (brand == null
                && carModel == null
                && bodyType == null
                && priceFrom == null
                && priceTo == null
                && fuelType == null
                && productionYear == null
                && mileageFrom == null
                && mileageTo == null);

        return result;
    }


}
