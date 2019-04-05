package academy.digitallab.onlinestore.productservice.api;

import academy.digitallab.onlinestore.productservice.domain.ProductService;
import academy.digitallab.onlinestore.productservice.domain.repository.entity.Category;
import academy.digitallab.onlinestore.productservice.domain.repository.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")

public class ProductRest {
    @Autowired
    ProductService productService;
    @Value("${eureka.instance.instance-id}")
    private String instanceId;


    // -------------------Retrieve All Products--------------------------------------------

    @GetMapping
    public ResponseEntity<List<Product>> listAllProducts() {
        log.info (  "Instace:{}", instanceId);
        List<Product> products = productService.findProductAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    // -------------------Retrieve  Product by Category------------------------------------------

    @GetMapping(value = "/category")
    public ResponseEntity<List<Product>>getProductByCategory(@RequestParam Long id) {
        log.info("Fetching Product with category id {}", id);
        Category category= new Category();
        category.setId(id);
        List<Product> products = productService.findByCategory(category);
        if (products == null) {
            log.error("Products with category id {} not found.", id);
            return new ResponseEntity<List<Product>>(products, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }
    // -------------------Retrieve Single Product------------------------------------------

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
        log.info("Fetching Product with id {} on instance { }", id, instanceId);
        Product product = productService.getProduct(id);
        if (product == null) {
            log.error("Product with id {} not found.", id);
            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // -------------------Create a Product-------------------------------------------

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        log.info("Creating Product : {}", product);


        productService.createProduct (product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    // ------------------- Update a Product ------------------------------------------------

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        log.info("Updating Product with id {}", id);

        Product currentProduct = productService.getProduct(id);

        if (currentProduct == null) {
            log.error("Unable to update. Product with id {} not found.", id);
            return new ResponseEntity<>(currentProduct,
                    HttpStatus.NOT_FOUND);
        }


        product.setId(id);
        productService.updateProduct(product);
        return new ResponseEntity<>(currentProduct, HttpStatus.OK);
    }

    // ------------------- Delete a Product-----------------------------------------

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Product with id {}", id);

        Product product = productService.getProduct(id);
        if (product == null) {
            log.error("Unable to delete. Product with id {} not found.", id);
            return new ResponseEntity<>(product,
                    HttpStatus.NOT_FOUND);
        }
        product = productService.deleteProduct(product);
        return new ResponseEntity<Product>(product,HttpStatus.OK);
    }

// -------------------Update Stock to Product-------------------------------------------

    @GetMapping("/{id}/stock")
    public ResponseEntity<Product> updateStockProduct( @PathVariable Long id ,@RequestParam( name = "quantity", required = true) Double quantity) {
        log.info("update stock Product : {} with quantity {} ", id, quantity);

       Product product= productService.updateStock(id, quantity);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
