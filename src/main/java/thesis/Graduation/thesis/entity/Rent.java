package thesis.Graduation.thesis.entity;


import jakarta.persistence.*;
import thesis.Graduation.thesis.entity.enums.RentStatus;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "rents")
public class Rent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rentNumber;

    @Column(nullable = false)
    private LocalDate rentStart;

    @Column(nullable = false)
    private LocalDate rentEnd;

    @Column(nullable = false)
    private LocalDate actualReturnDate;

    @Column(nullable = false)
    private Double dailyRate;

    @Column(nullable = false)
    private Double totalAmount;

    @Column(nullable = false)
    private Double deposit;

    @Column(nullable = false)
    private RentStatus status;

    private String notes;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(mappedBy = "rent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;

    public Rent() {
    }

    public Rent(String rentNumber, LocalDate rentStart, LocalDate rentEnd, LocalDate actualReturnDate, Double dailyRate, Double totalAmount, Double deposit, RentStatus status, String notes, Boolean active, Client client, Employee employee, Car car, List<Payment> payments) {
        this.rentNumber = rentNumber;
        this.rentStart = rentStart;
        this.rentEnd = rentEnd;
        this.actualReturnDate = actualReturnDate;
        this.dailyRate = dailyRate;
        this.totalAmount = totalAmount;
        this.deposit = deposit;
        this.status = status;
        this.notes = notes;
        this.active = active;
        this.client = client;
        this.employee = employee;
        this.car = car;
        this.payments = payments;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRentNumber() {
        return rentNumber;
    }

    public void setRentNumber(String rentNumber) {
        this.rentNumber = rentNumber;
    }

    public LocalDate getRentStart() {
        return rentStart;
    }

    public void setRentStart(LocalDate rentStart) {
        this.rentStart = rentStart;
    }

    public LocalDate getRentEnd() {
        return rentEnd;
    }

    public void setRentEnd(LocalDate rentEnd) {
        this.rentEnd = rentEnd;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public Double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(Double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public RentStatus getStatus() {
        return status;
    }

    public void setStatus(RentStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

}
