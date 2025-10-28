package thesis.Graduation.thesis.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.Salon;
import thesis.Graduation.thesis.repository.CarRepository;
import thesis.Graduation.thesis.repository.SalonRepository;

import java.util.List;

@Service
public class SalonService {

    private final SalonRepository salonRepository;
    private final CarRepository carRepository;

    public SalonService(SalonRepository salonRepository, CarRepository carRepository) {
        this.salonRepository = salonRepository;
        this.carRepository = carRepository;
    }


    public List<Salon> getAllSalons() {
        return salonRepository.findAll();
    }

    public Salon getSalonById(Long id) {
        return salonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salon not found with id " + id));
    }

    public Salon saveSalon(Salon salon) {
        // Проверка обязательных полей
        if (salon.getName() == null || salon.getCode() == null || salon.getPhone() == null ||
                salon.getEmail() == null || salon.getStreet() == null || salon.getBuildingNumber() == null ||
                salon.getApartmentNumber() == null || salon.getCity() == null || salon.getCountry() == null) {
            throw new IllegalArgumentException("Все обязательные поля салона должны быть заполнены");
        }

        return salonRepository.save(salon);
    }

    public void deleteSalon(Long id) {
        salonRepository.deleteById(id);
    }



    public List<Salon> getSalonsForCar(Long carId) {
        return salonRepository.findAllByCars_Id(carId);
    }

    @Transactional
    public void addCarToSalon(Long salonId, Long carId) {
        Salon salon = salonRepository.findById(salonId)
                .orElseThrow(() -> new IllegalArgumentException("Salon not found with ID: " + salonId));

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + carId));

        salon.getCars().add(car);
        car.setSalon(salon); // обновляем связь с салоном
        salonRepository.save(salon);
    }

    @Transactional
    public void addCarsToSalon(Long salonId, List<Long> carIds) {
        if (carIds == null || carIds.isEmpty()) {
            throw new IllegalArgumentException("Список автомобилей не может быть пустым");
        }

        Salon salon = salonRepository.findById(salonId)
                .orElseThrow(() -> new IllegalArgumentException("Salon not found with ID: " + salonId));

        List<Car> cars = carRepository.findAllById(carIds);
        for (Car car : cars) {
            car.setSalon(salon);
        }
        salon.getCars().addAll(cars);
        salonRepository.save(salon);
    }

    @Transactional
    public void removeCarFromSalon(Long salonId, Long carId) {
        Salon salon = salonRepository.findById(salonId)
                .orElseThrow(() -> new IllegalArgumentException("Salon not found with ID: " + salonId));

        salon.getCars().removeIf(c -> c.getId().equals(carId));
        carRepository.findById(carId).ifPresent(car -> car.setSalon(null));
        salonRepository.save(salon);
    }
}
