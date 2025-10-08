package thesis.Graduation.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import thesis.Graduation.thesis.entity.BodyType;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.FuelType;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT DISTINCT C.brand FROM Car C ORDER BY C.brand")
    List<String> findAllBrands();

    @Query("SELECT DISTINCT C.model FROM Car C")
    List<String> findAllModels();

    @Query("SELECT DISTINCT C.year FROM Car C ORDER BY C.year DESC")
    List<Integer> findAllYears();

    @Query("SELECT c FROM Car c WHERE " +
            "(:brand IS NOT NULL OR :model IS NOT NULL OR :bodyType IS NOT NULL " +
            " OR :fuelType IS NOT NULL OR :priceFrom IS NOT NULL OR :priceTo IS NOT NULL " +
            " OR :year IS NOT NULL OR :mileageFrom IS NOT NULL OR :mileageTo IS NOT NULL) " +
            "AND (:brand IS NULL OR c.brand = :brand) " +
            "AND (:model IS NULL OR c.model = :model) " +
            "AND (:bodyType IS NULL OR c.bodyType = :bodyType) " +
            "AND (:fuelType IS NULL OR c.fuelType = :fuelType) " +
            "AND (:priceFrom IS NULL OR c.price >= :priceFrom) " +
            "AND (:priceTo IS NULL OR c.price <= :priceTo) " +
            "AND (:year IS NULL OR c.year >= :year) " +
            "AND (:mileageFrom IS NULL OR c.mileage >= :mileageFrom) " +
            "AND (:mileageTo IS NULL OR c.mileage <= :mileageTo)")
    List<Car> findSearchCars(@Param("brand") String brand,
                             @Param("model") String carModel,
                             @Param("bodyType") BodyType bodyType,
                             @Param("priceFrom") Double priceFrom,
                             @Param("priceTo") Double priceTo,
                             @Param("fuelType") FuelType fuelType,
                             @Param("year") Integer year,
                             @Param("mileageFrom") Integer mileageFrom,
                             @Param("mileageTo") Integer mileageTo);


}
