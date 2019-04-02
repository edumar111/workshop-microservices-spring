package academy.digitallab.onlinestore.shoppingservice.domain.repository;

import academy.digitallab.onlinestore.shoppingservice.domain.repository.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
