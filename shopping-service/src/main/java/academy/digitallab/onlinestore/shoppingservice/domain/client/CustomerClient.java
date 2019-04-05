package academy.digitallab.onlinestore.shoppingservice.domain.client;

import academy.digitallab.onlinestore.shoppingservice.domain.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "customer-service" , fallback = CustomerHystrixFallbackFactory.class)
public interface CustomerClient {
    @GetMapping(value = "/customers/{id}")
    public ResponseEntity <Customer> getCustomer(@PathVariable("id") long id) ;

}
