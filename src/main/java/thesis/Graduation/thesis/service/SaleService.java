package thesis.Graduation.thesis.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thesis.Graduation.thesis.entity.*;
import thesis.Graduation.thesis.entity.enums.CarStatus;
import thesis.Graduation.thesis.entity.enums.PaymentMethod;
import thesis.Graduation.thesis.entity.enums.PaymentStatus;
import thesis.Graduation.thesis.repository.*;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    public SaleService(SaleRepository saleRepository, CarRepository carRepository,
                       ClientRepository clientRepository, EmployeeRepository employeeRepository,
                       PaymentRepository paymentRepository, InvoiceRepository invoiceRepository) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    @Transactional
    public Sale createSale(Long carId, Long clientId, Long employeeId,
                          PaymentMethod paymentMethod, String notes) {
        // Get entities
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        // Check if car is available
        if (car.getStatus() != CarStatus.AVAILABLE) {
            throw new IllegalStateException("Car is not available for sale");
        }

        // Create sale
        Sale sale = new Sale();
        sale.setSaleDate(LocalDate.now());
        sale.setTotalAmount(car.getPrice());
        sale.setPaymentMethod(paymentMethod);
        sale.setNotes(notes);
        sale.setClient(client);
        sale.setEmployee(employee);
        sale.setCar(car);

        // Update car status
        car.setStatus(CarStatus.SOLD);
        carRepository.save(car);

        // Create payment
        Payment payment = new Payment();
        payment.setDeuDate(LocalDate.now());
        payment.setPaymentDate(LocalDate.now());
        payment.setAmount(car.getPrice());
        payment.setMethod(paymentMethod);
        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setActive(true);
        payment.setSale(sale);

        sale.setPayment(payment);

        // Save sale (payment will be cascaded)
        return saleRepository.save(sale);
    }

    public long countSales() {
        return saleRepository.count();
    }

    public Double getTotalRevenue() {
        List<Sale> sales = saleRepository.findAll();
        return sales.stream()
                .mapToDouble(Sale::getTotalAmount)
                .sum();
    }

    public List<Sale> getSalesByEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) return List.of();
        return employee.getSales();
    }

    public List<Sale> getSalesByClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) return List.of();
        return client.getSales();
    }

    @Transactional
    public void deleteSale(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sale not found"));

        // Update car status back to available
        Car car = sale.getCar();
        if (car != null) {
            car.setStatus(CarStatus.AVAILABLE);
            carRepository.save(car);
        }

        saleRepository.delete(sale);
    }
}
