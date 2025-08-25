package thesis.Graduation.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import thesis.Graduation.thesis.entity.BodyType;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.EngineType;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT DISTINCT C.brand FROM Car C")
    List<String> findAllBrands();

    @Query("SELECT DISTINCT C.model FROM Car C")
    List<String> findAllModels();

    @Query("SELECT DISTINCT C.bodyType FROM Car C")
    List<String> findAllBodyTypes();

    @Query("SELECT DISTINCT C.engineType FROM Car C")
    List<String> findAllEngineTypes();

    @Query("SELECT DISTINCT C.year FROM Car C ORDER BY C.year DESC")
    List<Integer> findAllYears();

    @Query("SELECT C FROM Car C WHERE " +
            "(:brand is NULL OR C.brand = :brand)" +
            "AND (:model is NULL OR C.model = :model)" +
            "AND (:bodyType is NULL OR C.bodyType = :bodyType)" +
            "AND (:price is NULL OR C.price = :price)" +
            "AND (:engineType is NULL OR C.engineType = :engineType)" +
            "AND (:year is NULL OR C.year = :year)")
    List<Car> findSearchCars(@Param("brand") String brand,
                             @Param("model") String model,
                             @Param("bodyType") BodyType bodyType,
                             @Param("price") Double price,
                             @Param("engineType") EngineType engineType,
                             @Param("year") Integer year);


}
