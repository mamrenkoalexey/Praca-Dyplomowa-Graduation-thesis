package thesis.Graduation.thesis.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thesis.Graduation.thesis.entity.*;
import thesis.Graduation.thesis.entity.enums.CarStatus;
import thesis.Graduation.thesis.entity.enums.PaymentMethod;
import thesis.Graduation.thesis.entity.enums.PaymentStatus;
import thesis.Graduation.thesis.entity.enums.RentStatus;
import thesis.Graduation.thesis.repository.CarRepository;
import thesis.Graduation.thesis.repository.ClientRepository;
import thesis.Graduation.thesis.repository.EmployeeRepository;
import thesis.Graduation.thesis.repository.RentRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public RentService(RentRepository rentRepository,
                       CarRepository carRepository,
                       ClientRepository clientRepository,
                       EmployeeRepository employeeRepository) {
        this.rentRepository = rentRepository;
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    public Rent getRentById(Long id) {
        return rentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wynajem o ID " + id + " nie znaleziony"));
    }

    public List<Rent> getActiveRents() {
        return rentRepository.findAll().stream()
                .filter(r -> r.getStatus() == RentStatus.ACTIVE)
                .toList();
    }

    public List<Rent> getRentsByEmployee(Long employeeId) {
        return rentRepository.findAll().stream()
                .filter(r -> r.getEmployee() != null && r.getEmployee().getId().equals(employeeId))
                .toList();
    }

    public List<Rent> getRentsByClient(Long clientId) {
        return rentRepository.findAll().stream()
                .filter(r -> r.getClient() != null && r.getClient().getId().equals(clientId))
                .toList();
    }

    public long countRents() {
        return rentRepository.count();
    }

    public long countActiveRents() {
        return rentRepository.findAll().stream()
                .filter(r -> r.getStatus() == RentStatus.ACTIVE)
                .count();
    }

    public Double getTotalRentRevenue() {
        return rentRepository.findAll().stream()
                .filter(r -> r.getStatus() == RentStatus.COMPLETED)
                .mapToDouble(Rent::getTotalAmount)
                .sum();
    }

    public Double getPendingRentRevenue() {
        return rentRepository.findAll().stream()
                .filter(r -> r.getStatus() == RentStatus.ACTIVE)
                .mapToDouble(Rent::getTotalAmount)
                .sum();
    }

    @Transactional
    public Rent createRent(Long carId, Long clientId, Long employeeId,
                           LocalDate startDate, LocalDate endDate,
                           Double dailyRate, Double deposit, String notes) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Samochód nie znaleziony"));

        if (car.getStatus() != CarStatus.AVAILABLE) {
            throw new RuntimeException("Samochód nie jest dostępny do wynajmu");
        }

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Klient nie znaleziony"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Pracownik nie znaleziony"));

        long days = ChronoUnit.DAYS.between(startDate, endDate);
        if (days <= 0) {
            throw new RuntimeException("Data zakończenia musi być późniejsza niż data rozpoczęcia");
        }

        Double totalAmount = dailyRate * days;

        Rent rent = new Rent();
        rent.setRentNumber("RENT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        rent.setRentStart(startDate);
        rent.setRentEnd(endDate);
        rent.setActualReturnDate(endDate);
        rent.setDailyRate(dailyRate);
        rent.setTotalAmount(totalAmount);
        rent.setDeposit(deposit);
        rent.setStatus(RentStatus.ACTIVE);
        rent.setNotes(notes);
        rent.setActive(true);
        rent.setCar(car);
        rent.setClient(client);
        rent.setEmployee(employee);

        car.setStatus(CarStatus.RESERVED);
        carRepository.save(car);

        return rentRepository.save(rent);
    }

    @Transactional
    public Rent completeRent(Long rentId) {
        Rent rent = getRentById(rentId);

        if (rent.getStatus() != RentStatus.ACTIVE) {
            throw new RuntimeException("Tylko aktywny wynajem może zostać zakończony");
        }

        rent.setStatus(RentStatus.COMPLETED);
        rent.setActualReturnDate(LocalDate.now());
        rent.setActive(false);

        Car car = rent.getCar();
        car.setStatus(CarStatus.AVAILABLE);
        carRepository.save(car);

        return rentRepository.save(rent);
    }

    @Transactional
    public Rent completeRentWithLateReturn(Long rentId, LocalDate actualReturnDate) {
        Rent rent = getRentById(rentId);

        if (rent.getStatus() != RentStatus.ACTIVE) {
            throw new RuntimeException("Tylko aktywny wynajem może zostać zakończony");
        }

        rent.setActualReturnDate(actualReturnDate);

        if (actualReturnDate.isAfter(rent.getRentEnd())) {
            rent.setStatus(RentStatus.LATE_RETURN);
            long extraDays = ChronoUnit.DAYS.between(rent.getRentEnd(), actualReturnDate);
            Double extraCharge = rent.getDailyRate() * extraDays * 1.5;
            rent.setTotalAmount(rent.getTotalAmount() + extraCharge);
        } else {
            rent.setStatus(RentStatus.COMPLETED);
        }

        rent.setActive(false);

        Car car = rent.getCar();
        car.setStatus(CarStatus.AVAILABLE);
        carRepository.save(car);

        return rentRepository.save(rent);
    }

    @Transactional
    public Rent cancelRent(Long rentId, String reason) {
        Rent rent = getRentById(rentId);

        if (rent.getStatus() == RentStatus.COMPLETED || rent.getStatus() == RentStatus.LATE_RETURN) {
            throw new RuntimeException("Zakończony wynajem nie może zostać anulowany");
        }

        rent.setStatus(RentStatus.CANCELLED);
        rent.setNotes((rent.getNotes() != null ? rent.getNotes() + "\n" : "") + "Anulowano: " + reason);
        rent.setActive(false);

        Car car = rent.getCar();
        car.setStatus(CarStatus.AVAILABLE);
        carRepository.save(car);

        return rentRepository.save(rent);
    }

    @Transactional
    public void deleteRent(Long rentId) {
        Rent rent = getRentById(rentId);

        if (rent.getStatus() == RentStatus.ACTIVE) {
            Car car = rent.getCar();
            car.setStatus(CarStatus.AVAILABLE);
            carRepository.save(car);
        }

        rentRepository.delete(rent);
    }

    public long getRentDurationInDays(Rent rent) {
        return ChronoUnit.DAYS.between(rent.getRentStart(), rent.getRentEnd());
    }

    public String getFormattedDailyRate(Rent rent) {
        return String.format("%,.2f", rent.getDailyRate());
    }

    public String getFormattedTotalAmount(Rent rent) {
        return String.format("%,.2f", rent.getTotalAmount());
    }

    public String getFormattedDeposit(Rent rent) {
        return String.format("%,.2f", rent.getDeposit());
    }
}
