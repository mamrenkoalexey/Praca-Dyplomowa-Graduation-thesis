package thesis.Graduation.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thesis.Graduation.thesis.entity.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {
}
