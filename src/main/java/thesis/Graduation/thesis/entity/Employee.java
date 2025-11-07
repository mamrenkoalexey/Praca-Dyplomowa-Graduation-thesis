package thesis.Graduation.thesis.entity;


import jakarta.persistence.*;
import thesis.Graduation.thesis.entity.enums.Role;

import java.util.List;

@Entity
@Table(name = "employees")
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "salon_id")
    private Salon salon;

    @Column(nullable = false)
    private Double salary;

    @Column(nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "employee")
    private List<Lease> leases;

    @OneToMany(mappedBy = "employee")
    private List<Rent> rents;

    @OneToMany(mappedBy = "employee")
    private List<Sale> sales;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Role role, String login, String password, String email, String phone, Salon salon, Double salary, Boolean active, List<Lease> leases, List<Rent> rents, List<Sale> sales) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.salon = salon;
        this.salary = salary;
        this.active = active;
        this.leases = leases;
        this.rents = rents;
        this.sales = sales;
    }

    public Boolean getActive() {
        return active;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public boolean isActive() {
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

    public List<Rent> getRents() {
        return rents;
    }

    public void setRents(List<Rent> rents) {
        this.rents = rents;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
