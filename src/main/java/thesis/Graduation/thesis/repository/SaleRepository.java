package thesis.Graduation.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thesis.Graduation.thesis.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
