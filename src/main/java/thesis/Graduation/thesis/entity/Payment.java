package thesis.Graduation.thesis.entity;

import jakarta.persistence.*;
import thesis.Graduation.thesis.entity.enums.PaymentMethod;
import thesis.Graduation.thesis.entity.enums.PaymentStatus;

import java.time.LocalDate;

@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate deuDate;

    @Column(nullable = false)
    private LocalDate paymentDate;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(nullable = false)
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "lease_id")
    private Lease lease;

    @ManyToOne
    @JoinColumn(name = "rent_id")
    private Rent rent;

    @OneToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;


    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public Payment() {
    }

    public Payment(Long id, LocalDate deuDate, LocalDate paymentDate, Double amount, PaymentMethod method, PaymentStatus status, Boolean active, Lease lease, Rent rent, Invoice invoice) {
        this.id = id;
        this.deuDate = deuDate;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.active = active;
        this.lease = lease;
        this.rent = rent;
        this.invoice = invoice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDeuDate() {
        return deuDate;
    }

    public void setDeuDate(LocalDate deuDate) {
        this.deuDate = deuDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}

