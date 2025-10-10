package thesis.Graduation.thesis.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User client;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(nullable = false)
    private StatusOrder status;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Order() {
    }

    public Order(User client, Car car, StatusOrder status) {
        this.client = client;
        this.car = car;
        this.status = status;
    }
}
