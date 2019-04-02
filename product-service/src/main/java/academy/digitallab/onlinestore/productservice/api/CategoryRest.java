package academy.digitallab.onlinestore.productservice.api;

import academy.digitallab.onlinestore.productservice.domain.ProductService;
import academy.digitallab.onlinestore.productservice.domain.repository.entity.Category;
import academy.digitallab.onlinestore.productservice.domain.repository.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping( value = "/categories")
public class CategoryRest {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categories = productService.findCategoryAll();
        if (categories.isEmpty()){
            return new ResponseEntity<List<Category>>(categories, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Category getCategory( @PathVariable Long id){
        log.info("Fetching Category with id {}", id);
        return productService.getCategory(id);
    }


    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        log.info("Creating Category :{}", category);
        return new ResponseEntity<Category>(productService.createCategory(category), HttpStatus.CREATED)  ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Long id){
        log.info("Updating Category with id {}", id);
        category.setId(id);
       Category currentCategory= productService.updateCategory(category );
        if (currentCategory == null) {
            log.error("Unable to update. Category with id {} not found.", id);
            return new ResponseEntity<>(currentCategory,
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(currentCategory, HttpStatus.OK)  ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id){
        log.info("Fetching & Deleting Category with id {}", id);
        Category currentCategory= productService.getCategory(id);
        if (currentCategory == null) {
            log.error("Unable to update. Category with id {} not found.", id);
            return new ResponseEntity<>(currentCategory,
                    HttpStatus.NOT_FOUND);
        }
        productService.deleteCategory(id);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }
}
