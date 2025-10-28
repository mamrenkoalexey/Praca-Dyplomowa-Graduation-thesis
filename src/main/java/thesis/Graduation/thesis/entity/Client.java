package thesis.Graduation.thesis.entity;

import jakarta.persistence.*;
import thesis.Graduation.thesis.entity.enums.TaxNumberType;

import java.util.List;

@Entity
@Table(name = "clients")
public class Client extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String companyName;

    @Column(nullable = false)
    private String taxNumber;

    @Enumerated(EnumType.STRING)
    private TaxNumberType taxNumberType;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "client")
    private List<Lease> leases;

    @OneToMany(mappedBy = "client")
    private List<Sale> sales;

    @OneToMany(mappedBy = "client")
    private List<Rent> rents;

    @OneToMany(mappedBy = "client")
    private List<Invoice> invoices;

    public Client() {
    }

    public Client(Long id, String firstName, String lastName, String companyName, String taxNumber, TaxNumberType taxNumberType, String email, String login, String password, String phone, Boolean active, List<Lease> leases, List<Sale> sales, List<Rent> rents, List<Invoice> invoices) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.taxNumber = taxNumber;
        this.taxNumberType = taxNumberType;
        this.email = email;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.active = active;
        this.leases = leases;
        this.sales = sales;
        this.rents = rents;
        this.invoices = invoices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public TaxNumberType getTaxNumberType() {
        return taxNumberType;
    }

    public void setTaxNumberType(TaxNumberType taxNumberType) {
        this.taxNumberType = taxNumberType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Lease> getLeases() {
        return leases;
    }

    public void setLeases(List<Lease> leases) {
        this.leases = leases;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
