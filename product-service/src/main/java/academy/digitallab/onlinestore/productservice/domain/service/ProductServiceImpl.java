package academy.digitallab.onlinestore.productservice.domain.service;

import academy.digitallab.onlinestore.productservice.domain.ProductService;
import academy.digitallab.onlinestore.productservice.domain.repository.CategoryRepository;
import academy.digitallab.onlinestore.productservice.domain.repository.ProductRepository;
import academy.digitallab.onlinestore.productservice.domain.repository.entity.Category;
import academy.digitallab.onlinestore.productservice.domain.repository.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Product> findProductAll() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("CREATED");
        product.setCreateAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Product productDB = getProduct(product.getId());
        if (productDB == null){
            return null;
        }
        productDB.setDescription(product.getDescription());
        productDB.setName(product.getName());
        productDB.setPrice(product.getPrice());
        return productRepository.save(productDB);
    }

    @Override
    public Product deleteProduct(Product product) {
        Product productDB = getProduct(product.getId());
        productDB.setStatus("DELETED");
        if (productDB == null){
            return null;
        }
        return productRepository.save(productDB);
    }


    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Product productDB = getProduct(id);
        Double stock = productDB.getStock() - quantity;
        productDB.setStock(stock);

        return productRepository.save(productDB);
    }

    //---CATEGORY--------------------------------------------
    @Override
    public Category createCategory(Category category) {
         return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        Category categoryDB = getCategory(category.getId());
        if (categoryDB == null){
            return null;
        }
        categoryDB.setName(category.getName());
        return categoryRepository.save(categoryDB);


    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCategory(Long id) {

        categoryRepository.deleteById(id);
    }




    @Override
    public List<Category> findCategoryAll() {
        return categoryRepository.findAll();
    }
}
