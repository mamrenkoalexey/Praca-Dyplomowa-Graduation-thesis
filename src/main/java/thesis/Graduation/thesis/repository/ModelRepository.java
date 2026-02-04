package thesis.Graduation.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import thesis.Graduation.thesis.entity.Model;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    String findByName(String name);
    boolean existsByNameIgnoreCaseAndBrandId(String name, Long brandId);

    @Query("SELECT m FROM Model m WHERE m.brand.id = :brandId ORDER BY m.name ASC")
    List<Model> findByBrandId(@Param("brandId") Long brandId);
}
