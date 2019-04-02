package academy.digitallab.onlinestore.productservice.domain;

import academy.digitallab.onlinestore.productservice.domain.repository.entity.Category;
import academy.digitallab.onlinestore.productservice.domain.repository.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findProductAll();
    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public Product deleteProduct(Product product);
    public Product getProduct(Long id);
    public List<Product> findByCategory(Category category);
    public Product updateStock(Long id, Double quantity);

    public Category createCategory(Category category);
    public Category updateCategory(Category category);
    public Category getCategory(Long id);
    public void deleteCategory(Long id);
    public List<Category> findCategoryAll();
}
