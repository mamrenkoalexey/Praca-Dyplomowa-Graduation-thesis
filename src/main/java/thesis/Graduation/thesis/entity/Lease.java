package thesis.Graduation.thesis.entity;

import jakarta.persistence.*;
import thesis.Graduation.thesis.entity.enums.LeaseStatus;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "leases")
public class Lease extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contractNumber;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private Double initialPayment;

    @Column(nullable = false)
    private Double monthlyPayment;

    @Column(nullable = false)
    private Double totalValue;

    @Enumerated(EnumType.STRING)
    private LeaseStatus status;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(mappedBy = "lease", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;

    public Lease() {
    }

    public Lease(List<Payment> payments, Car car, Employee employee, Client client, String notes, LeaseStatus status, Double totalValue, Double monthlyPayment, Double initialPayment, LocalDate endDate, LocalDate startDate, String contractNumber) {
        this.payments = payments;
        this.car = car;
        this.employee = employee;
        this.client = client;
        this.notes = notes;
        this.status = status;
        this.totalValue = totalValue;
        this.monthlyPayment = monthlyPayment;
        this.initialPayment = initialPayment;
        this.endDate = endDate;
        this.startDate = startDate;
        this.contractNumber = contractNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment(Double initialPayment) {
        this.initialPayment = initialPayment;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public LeaseStatus getStatus() {
        return status;
    }

    public void setStatus(LeaseStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    // Helper methods for formatted display
    public String getFormattedInitialPayment() {
        return String.format("%,.2f", initialPayment);
    }

    public String getFormattedMonthlyPayment() {
        return String.format("%,.2f", monthlyPayment);
    }

    public String getFormattedTotalValue() {
        return String.format("%,.2f", totalValue);
    }

    public long getLeaseDurationInMonths() {
        if (startDate != null && endDate != null) {
            return java.time.temporal.ChronoUnit.MONTHS.between(startDate, endDate);
        }
        return 0;
    }

    public double getTotalPaid() {
        if (payments == null) return 0.0;
        return payments.stream()
                .filter(p -> p.getStatus() == thesis.Graduation.thesis.entity.enums.PaymentStatus.COMPLETED)
                .mapToDouble(Payment::getAmount)
                .sum();
    }

    public double getRemainingAmount() {
        return totalValue - getTotalPaid();
    }

    public String getFormattedTotalPaid() {
        return String.format("%,.2f", getTotalPaid());
    }

    public String getFormattedRemainingAmount() {
        return String.format("%,.2f", getRemainingAmount());
    }
}
