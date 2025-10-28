package thesis.Graduation.thesis.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String invoiceNumber;

    @Column(nullable = false)
    private LocalDate invoiceDate;

    @Column(nullable = false)
    private String invoiceName;

    @Column(nullable = false)
    private String invoiceTaxNumber;

    @Column(nullable = false)
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<Payment> payments;

    public Invoice() {
    }

    public Invoice(Long id, String invoiceNumber, LocalDate invoiceDate, String invoiceName, String invoiceTaxNumber, Double totalAmount, Client client, List<Payment> payments) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.invoiceName = invoiceName;
        this.invoiceTaxNumber = invoiceTaxNumber;
        this.totalAmount = totalAmount;
        this.client = client;
        this.payments = payments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public String getInvoiceTaxNumber() {
        return invoiceTaxNumber;
    }

    public void setInvoiceTaxNumber(String invoiceTaxNumber) {
        this.invoiceTaxNumber = invoiceTaxNumber;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
