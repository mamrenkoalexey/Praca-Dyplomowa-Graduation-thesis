package thesis.Graduation.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thesis.Graduation.thesis.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
