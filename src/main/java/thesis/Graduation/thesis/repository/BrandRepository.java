package thesis.Graduation.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thesis.Graduation.thesis.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
