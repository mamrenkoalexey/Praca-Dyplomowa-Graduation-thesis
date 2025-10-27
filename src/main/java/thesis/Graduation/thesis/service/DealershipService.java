package thesis.Graduation.thesis.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.Dealership;
import thesis.Graduation.thesis.repository.CarRepository;
import thesis.Graduation.thesis.repository.DealershipRepository;

import java.util.List;

@Service
public class DealershipService {

    private final DealershipRepository dealershipRepository;
    private final CarRepository carRepository;


    public DealershipService(DealershipRepository dealershipRepository, CarRepository carRepository) {
        this.dealershipRepository = dealershipRepository;
        this.carRepository = carRepository;
    }

    public List<Dealership> getAllDealerships() {
        return dealershipRepository.findAll();
    }

    public Dealership getDealershipById(Long id) {
        return dealershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dealership not found"));
    }

    public Dealership saveDealership(Dealership dealership) {
        return dealershipRepository.save(dealership);
    }

    public void deleteDealership(Long id) {
        dealershipRepository.deleteById(id);
    }

    public List<Dealership> getDealershipsForCar(Long carId) {
        return dealershipRepository.findAllByCars_Id(carId);
    }

    @Transactional
    public void addCarToDealership(Long dealershipId, Long carId) {
        Dealership dealership = dealershipRepository.findById(dealershipId)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono salonu o ID: " + dealershipId));

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono samochodu o ID: " + carId));

        dealership.getCars().add(car);
        dealershipRepository.save(dealership);
    }

    @Transactional
    public void addCarsToDealership(Long dealershipId, List<Long> carIds) {
        Dealership dealership = dealershipRepository.findById(dealershipId)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono salonu o ID: " + dealershipId));

        if (carIds == null || carIds.isEmpty()) {
            throw new IllegalArgumentException("Lista samochodów nie może być pusta.");
        }

        List<Car> cars = carRepository.findAllById(carIds);

        dealership.getCars().addAll(cars);
        dealershipRepository.save(dealership);
    }

    @Transactional
    public void removeCarFromDealership(Long dealershipId, Long carId) {
        Dealership dealership = dealershipRepository.findById(dealershipId)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono salonu o ID: " + dealershipId));

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono samochodu o ID: " + carId));

        dealership.getCars().removeIf(c -> c.getId().equals(carId));
        dealershipRepository.save(dealership);
    }


}