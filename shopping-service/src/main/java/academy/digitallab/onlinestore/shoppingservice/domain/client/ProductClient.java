package academy.digitallab.onlinestore.shoppingservice.domain.client;

import academy.digitallab.onlinestore.shoppingservice.domain.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service")
@RequestMapping("/products")
public interface ProductClient {
    @GetMapping(value = "/{id}")
    public ResponseEntity <Product> getProduct(@PathVariable("id") long id);

    @GetMapping("/{id}/stock")
    public ResponseEntity<Product> updateStockProduct( @PathVariable("id") long id ,@RequestParam( name = "quantity", required = true) Double quantity) ;

    }
