package thesis.Graduation.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import thesis.Graduation.thesis.entity.Car;
import thesis.Graduation.thesis.entity.Model;
import thesis.Graduation.thesis.entity.enums.CarStatus;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

    @Query("SELECT DISTINCT C.productionYear FROM Car C ORDER BY C.productionYear DESC")
    List<Integer> findAllProductionYear();

    List<Car> findByStatus(CarStatus status);

    @Query("SELECT DISTINCT c.model FROM Car c WHERE c.model.brand.id = :brand ORDER BY c.model.name ASC")
    List<Model> findModelsByBrand(@Param("brand") Long brand);


}
