package thesis.Graduation.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thesis.Graduation.thesis.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}
