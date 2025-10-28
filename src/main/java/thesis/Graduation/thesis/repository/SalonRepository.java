package thesis.Graduation.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thesis.Graduation.thesis.entity.Salon;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {
    Optional<Salon> findByName(String name);

    List<Salon> findByCity(String city);

    List<Salon> findAllByCars_Id(Long carId);
}