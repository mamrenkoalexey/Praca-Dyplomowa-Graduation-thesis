package thesis.Graduation.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import thesis.Graduation.thesis.entity.BodyType;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.FuelType;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

    @Query("SELECT DISTINCT C.brand FROM Car C ORDER BY C.brand")
    List<String> findAllBrands();

    @Query("SELECT DISTINCT C.model FROM Car C")
    List<String> findAllModels();

    @Query("SELECT DISTINCT C.year FROM Car C ORDER BY C.year DESC")
    List<Integer> findAllYears();
}
