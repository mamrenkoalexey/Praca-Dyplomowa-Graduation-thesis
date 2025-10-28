package thesis.Graduation.thesis.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "models")
public class Model extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String generation;

    @Column(nullable = false)
    private Integer yearFrom;

    @Column(nullable = false)
    private Integer yearTo;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars;

    public Model() {
    }

    public Model(Long id, String name, String generation, Integer yearFrom, Integer yearTo, Brand brand, List<Car> cars) {
        this.id = id;
        this.name = name;
        this.generation = generation;
        this.yearFrom = yearFrom;
        this.yearTo = yearTo;
        this.brand = brand;
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public Integer getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(Integer yearFrom) {
        this.yearFrom = yearFrom;
    }

    public Integer getYearTo() {
        return yearTo;
    }

    public void setYearTo(Integer yearTo) {
        this.yearTo = yearTo;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
