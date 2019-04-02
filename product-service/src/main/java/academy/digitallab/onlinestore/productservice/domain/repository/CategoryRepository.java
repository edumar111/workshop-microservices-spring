package academy.digitallab.onlinestore.productservice.domain.repository;

import academy.digitallab.onlinestore.productservice.domain.repository.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
