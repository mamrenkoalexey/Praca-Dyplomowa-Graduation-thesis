package thesis.Graduation.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thesis.Graduation.thesis.entity.Dealership;

import java.util.List;
import java.util.Optional;

@Repository
public interface DealershipRepository extends JpaRepository<Dealership, Long> {
    Optional<Dealership> findByName(String name);

    List<Dealership> findByCity(String city);

    List<Dealership> findAllByCars_Id(Long carId);
}