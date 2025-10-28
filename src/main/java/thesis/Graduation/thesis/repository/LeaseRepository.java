package thesis.Graduation.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thesis.Graduation.thesis.entity.Lease;

@Repository
public interface LeaseRepository extends JpaRepository<Lease, Long> {
}
