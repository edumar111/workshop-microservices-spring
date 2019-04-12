package academy.digitallab.onlinestore.shoppingservice.domain.client;

import academy.digitallab.onlinestore.shoppingservice.domain.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerHystrixFallbackFactory  implements  CustomerClient{
    @Override
    public ResponseEntity <Customer> getCustomer(long id) {
        ResponseEntity<Customer> response = new ResponseEntity<Customer> (new Customer () ,  HttpStatus.OK);
        log.info ( "CustomerHystrixFallbackFactory id {} ", id );
        return response;
    }
}
