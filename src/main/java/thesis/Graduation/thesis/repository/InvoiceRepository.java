package thesis.Graduation.thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thesis.Graduation.thesis.entity.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
