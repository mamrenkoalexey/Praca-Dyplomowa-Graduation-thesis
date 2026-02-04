package thesis.Graduation.thesis.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thesis.Graduation.thesis.entity.*;
import thesis.Graduation.thesis.entity.enums.CarStatus;
import thesis.Graduation.thesis.entity.enums.LeaseStatus;
import thesis.Graduation.thesis.entity.enums.PaymentMethod;
import thesis.Graduation.thesis.entity.enums.PaymentStatus;
import thesis.Graduation.thesis.repository.LeaseRepository;
import thesis.Graduation.thesis.repository.PaymentRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LeaseService {

    private final LeaseRepository leaseRepository;
    private final CarService carService;
    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final PaymentRepository paymentRepository;

    public LeaseService(LeaseRepository leaseRepository,
                       CarService carService,
                       ClientService clientService,
                       EmployeeService employeeService,
                       PaymentRepository paymentRepository) {
        this.leaseRepository = leaseRepository;
        this.carService = carService;
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.paymentRepository = paymentRepository;
    }

    /**
     * Get all leases
     */
    public List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }

    /**
     * Get lease by ID
     */
    public Lease getLeaseById(Long id) {
        return leaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lease not found with id: " + id));
    }

    /**
     * Get active leases
     */
    public List<Lease> getActiveLeases() {
        return leaseRepository.findAll().stream()
                .filter(lease -> lease.getStatus() == LeaseStatus.ACTIVE)
                .toList();
    }

    /**
     * Get leases by client
     */
    public List<Lease> getLeasesByClient(Long clientId) {
        return leaseRepository.findAll().stream()
                .filter(lease -> lease.getClient().getId().equals(clientId))
                .toList();
    }

    /**
     * Get leases by employee
     */
    public List<Lease> getLeasesByEmployee(Long employeeId) {
        return leaseRepository.findAll().stream()
                .filter(lease -> lease.getEmployee().getId().equals(employeeId))
                .toList();
    }

    /**
     * Create a new lease
     */
    @Transactional
    public Lease createLease(Long carId, Long clientId, Long employeeId,
                            LocalDate startDate, LocalDate endDate,
                            Double initialPayment, Double monthlyPayment,
                            String notes) {

        // Validate inputs
        if (startDate.isAfter(endDate)) {
            throw new RuntimeException("Data rozpoczęcia nie może być późniejsza niż data zakończenia");
        }

        if (startDate.isBefore(LocalDate.now())) {
            throw new RuntimeException("Data rozpoczęcia nie może być w przeszłości");
        }

        if (initialPayment < 0 || monthlyPayment < 0) {
            throw new RuntimeException("Płatności nie mogą być ujemne");
        }

        // Get entities
        Car car = carService.getCarById(carId);
        Client client = clientService.getClientById(clientId);
        Employee employee = employeeService.getEmployeeById(employeeId);

        // Check if car is available
        if (car.getStatus() != CarStatus.AVAILABLE) {
            throw new RuntimeException("Samochód nie jest dostępny do leasingu");
        }

        // Calculate lease duration in months
        long months = ChronoUnit.MONTHS.between(startDate, endDate);
        if (months <= 0) {
            throw new RuntimeException("Okres leasingu musi wynosić co najmniej 1 miesiąc");
        }

        // Calculate total value
        double totalValue = initialPayment + (monthlyPayment * months);

        // Generate unique contract number
        String contractNumber = "LEASE-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        // Create lease
        Lease lease = new Lease();
        lease.setContractNumber(contractNumber);
        lease.setStartDate(startDate);
        lease.setEndDate(endDate);
        lease.setInitialPayment(initialPayment);
        lease.setMonthlyPayment(monthlyPayment);
        lease.setTotalValue(totalValue);
        lease.setStatus(LeaseStatus.PENDING);
        lease.setNotes(notes);
        lease.setCar(car);
        lease.setClient(client);
        lease.setEmployee(employee);

        // Save lease
        lease = leaseRepository.save(lease);

        // Update car status
        car.setStatus(CarStatus.RESERVED);
        carService.updateCar(car.getId(), car);

        // Create payment schedule
        createPaymentSchedule(lease, months);

        return lease;
    }

    /**
     * Create payment schedule for lease
     */
    private void createPaymentSchedule(Lease lease, long months) {
        List<Payment> payments = new ArrayList<>();

        // Initial payment
        Payment initialPayment = new Payment();
        initialPayment.setDeuDate(lease.getStartDate());
        initialPayment.setPaymentDate(null);
        initialPayment.setAmount(lease.getInitialPayment());
        initialPayment.setMethod(null);
        initialPayment.setStatus(PaymentStatus.PENDING);
        initialPayment.setActive(true);
        initialPayment.setLease(lease);
        payments.add(paymentRepository.save(initialPayment));

        // Monthly payments
        for (int i = 1; i <= months; i++) {
            Payment payment = new Payment();
            payment.setDeuDate(lease.getStartDate().plusMonths(i));
            payment.setPaymentDate(null);
            payment.setAmount(lease.getMonthlyPayment());
            payment.setMethod(null);
            payment.setStatus(PaymentStatus.PENDING);
            payment.setActive(true);
            payment.setLease(lease);
            payments.add(paymentRepository.save(payment));
        }

        lease.setPayments(payments);
    }

    /**
     * Activate lease
     */
    @Transactional
    public Lease activateLease(Long leaseId) {
        Lease lease = getLeaseById(leaseId);

        if (lease.getStatus() != LeaseStatus.PENDING) {
            throw new RuntimeException("Tylko leasing oczekujący może zostać aktywowany");
        }

        lease.setStatus(LeaseStatus.ACTIVE);
        Car car = lease.getCar();
        car.setStatus(CarStatus.UNAVAILABLE);
        carService.updateCar(car.getId(), car);

        return leaseRepository.save(lease);
    }

    /**
     * Complete lease
     */
    @Transactional
    public Lease completeLease(Long leaseId) {
        Lease lease = getLeaseById(leaseId);

        if (lease.getStatus() != LeaseStatus.ACTIVE) {
            throw new RuntimeException("Tylko aktywny leasing może zostać zakończony");
        }

        // Check if all payments are completed
        boolean allPaid = lease.getPayments().stream()
                .allMatch(payment -> payment.getStatus() == PaymentStatus.COMPLETED);

        if (!allPaid) {
            throw new RuntimeException("Wszystkie płatności muszą być zrealizowane przed zakończeniem leasingu");
        }

        lease.setStatus(LeaseStatus.COMPLETED);
        Car car = lease.getCar();
        car.setStatus(CarStatus.AVAILABLE);
        carService.updateCar(car.getId(), car);

        return leaseRepository.save(lease);
    }

    /**
     * Terminate lease
     */
    @Transactional
    public Lease terminateLease(Long leaseId, String reason) {
        Lease lease = getLeaseById(leaseId);

        if (lease.getStatus() == LeaseStatus.COMPLETED || lease.getStatus() == LeaseStatus.TERMINATED) {
            throw new RuntimeException("Nie można rozwiązać zakończonego lub już rozwiązanego leasingu");
        }

        lease.setStatus(LeaseStatus.TERMINATED);
        lease.setNotes(lease.getNotes() + "\n[TERMINATED] Powód: " + reason);

        // Cancel all pending payments
        lease.getPayments().stream()
                .filter(payment -> payment.getStatus() == PaymentStatus.PENDING)
                .forEach(payment -> {
                    payment.setStatus(PaymentStatus.CANCELLED);
                    payment.setActive(false);
                    paymentRepository.save(payment);
                });

        Car car = lease.getCar();
        car.setStatus(CarStatus.AVAILABLE);
        carService.updateCar(car.getId(), car);

        return leaseRepository.save(lease);
    }

    /**
     * Process payment for lease
     */
    @Transactional
    public Payment processPayment(Long leaseId, Long paymentId, PaymentMethod method) {
        Lease lease = getLeaseById(leaseId);
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Płatność nie znaleziona"));

        if (!payment.getLease().getId().equals(leaseId)) {
            throw new RuntimeException("Płatność nie należy do tego leasingu");
        }

        if (payment.getStatus() == PaymentStatus.COMPLETED) {
            throw new RuntimeException("Płatność została już zrealizowana");
        }

        payment.setPaymentDate(LocalDate.now());
        payment.setMethod(method);
        payment.setStatus(PaymentStatus.COMPLETED);

        return paymentRepository.save(payment);
    }


    public Double getTotalLeaseRevenue() {
        return leaseRepository.findAll().stream()
                .filter(lease -> lease.getStatus() == LeaseStatus.ACTIVE || lease.getStatus() == LeaseStatus.PENDING)
                .mapToDouble(Lease::getTotalValue)
                .sum();
    }


    public List<Payment> getPendingPayments(Long leaseId) {
        Lease lease = getLeaseById(leaseId);
        return lease.getPayments().stream()
                .filter(payment -> payment.getStatus() == PaymentStatus.PENDING)
                .toList();
    }

    /**
     * Get completed payments for a lease
     */
    public List<Payment> getCompletedPayments(Long leaseId) {
        Lease lease = getLeaseById(leaseId);
        return lease.getPayments().stream()
                .filter(payment -> payment.getStatus() == PaymentStatus.COMPLETED)
                .toList();
    }

    /**
     * Update lease
     */
    @Transactional
    public Lease updateLease(Lease lease) {
        return leaseRepository.save(lease);
    }

    /**
     * Delete lease
     */
    @Transactional
    public void deleteLease(Long id) {
        Lease lease = getLeaseById(id);

        if (lease.getStatus() == LeaseStatus.ACTIVE) {
            throw new RuntimeException("Nie można usunąć aktywnego leasingu");
        }

        // Delete all associated payments
        if (lease.getPayments() != null) {
            paymentRepository.deleteAll(lease.getPayments());
        }

        // Make car available again
        Car car = lease.getCar();
        if (car != null && car.getStatus() == CarStatus.RESERVED) {
            car.setStatus(CarStatus.AVAILABLE);
            carService.updateCar(car.getId(), car);
        }

        leaseRepository.delete(lease);
    }
}
