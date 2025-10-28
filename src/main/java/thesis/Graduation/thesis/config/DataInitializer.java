package thesis.Graduation.thesis.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import thesis.Graduation.thesis.repository.*;

@Component
public class DataInitializer {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final CarRepository carRepository;
    private final SalonRepository salonRepository;
    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;
    private final SaleRepository saleRepository;
    private final LeaseRepository leaseRepository;
    private final RentRepository rentRepository;
    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    public DataInitializer(BrandRepository brandRepository,
                           ModelRepository modelRepository,
                           CarRepository carRepository,
                           SalonRepository salonRepository,
                           EmployeeRepository employeeRepository,
                           ClientRepository clientRepository,
                           SaleRepository saleRepository,
                           LeaseRepository leaseRepository,
                           RentRepository rentRepository,
                           PaymentRepository paymentRepository,
                           InvoiceRepository invoiceRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.carRepository = carRepository;
        this.salonRepository = salonRepository;
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
        this.saleRepository = saleRepository;
        this.leaseRepository = leaseRepository;
        this.rentRepository = rentRepository;
        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @PostConstruct
    @Transactional
    public void init() {

    }
}
