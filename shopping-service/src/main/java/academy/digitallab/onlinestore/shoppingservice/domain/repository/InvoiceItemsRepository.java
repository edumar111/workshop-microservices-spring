package academy.digitallab.onlinestore.shoppingservice.domain.repository;

import academy.digitallab.onlinestore.shoppingservice.domain.repository.entity.InvoiceItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItems,Long> {
}
