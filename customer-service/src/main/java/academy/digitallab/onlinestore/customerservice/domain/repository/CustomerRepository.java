package academy.digitallab.onlinestore.customerservice.domain.repository;

import academy.digitallab.onlinestore.customerservice.domain.repository.entity.Customer;

import academy.digitallab.onlinestore.customerservice.domain.repository.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository  extends JpaRepository<Customer,Long> {
        public List<Customer> findByRegion(Region region);
}
