package academy.digitallab.onlinestore.productservice.domain.repository;

import academy.digitallab.onlinestore.productservice.domain.repository.entity.Category;
import academy.digitallab.onlinestore.productservice.domain.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByCategory(Category category);

}
