package academy.digitallab.onlinestore.customerservice.api;

import academy.digitallab.onlinestore.customerservice.domain.CustomerService;
import academy.digitallab.onlinestore.customerservice.domain.repository.entity.Customer;
import academy.digitallab.onlinestore.customerservice.domain.repository.entity.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <h1>CustomerRest<h1/>
 * This class Resource Customer for Rest Api
 * @author Eduaro Marchena @edumar111
 * @version 1.0
 * @since 2019
 * **/
@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerRest {

    @Autowired
    CustomerService customerService;

    // -------------------Retrieve All Customers--------------------------------------------

    @GetMapping
    public ResponseEntity<List<Customer>> listAllCustomers() {
        List<Customer> Customers = customerService.findCustomerAll();
        if (Customers.isEmpty()) {
            return new ResponseEntity<>(Customers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Customers, HttpStatus.OK);
    }
    // -------------------Retrieve  Customer by Region------------------------------------------

    @GetMapping(value = "/region")
    public ResponseEntity<List<Customer>>getCustomerByRegion(@RequestParam(name = "id") Long id ) {
        log.info("Fetching Customer with Region id {}", id);
        Region Region= new Region();
        Region.setId(id);
        List<Customer> Customers = customerService.findCustomersByRegion(Region);
        if (Customers == null) {
            log.error("Customers with Region id {} not found.", id);
            return new ResponseEntity<List<Customer>>(Customers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Customer>>(Customers, HttpStatus.OK);
    }
    // -------------------Retrieve Single Customer------------------------------------------

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {
        log.info("Fetching Customer with id {}", id);
        Customer Customer = customerService.getCustomer(id);
        if (Customer == null) {
            log.error("Customer with id {} not found.", id);
            return new ResponseEntity<>(Customer, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Customer, HttpStatus.OK);
    }

    // -------------------Create a Customer-------------------------------------------

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer Customer) {
        log.info("Creating Customer : {}", Customer);


        customerService.createCustomer (Customer);

        return new ResponseEntity<>(Customer, HttpStatus.CREATED);
    }

    // ------------------- Update a Customer ------------------------------------------------

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer Customer) {
        log.info("Updating Customer with id {}", id);

        Customer currentCustomer = customerService.getCustomer(id);

        if (currentCustomer == null) {
            log.error("Unable to update. Customer with id {} not found.", id);
            return new ResponseEntity<>(currentCustomer,
                    HttpStatus.NOT_FOUND);
        }

        Customer.setId(id);

        currentCustomer=customerService.updateCustomer(Customer);
        return new ResponseEntity<>(currentCustomer, HttpStatus.OK);
    }

    // ------------------- Delete a Customer-----------------------------------------

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Customer with id {}", id);

        Customer Customer = customerService.getCustomer(id);
        if (Customer == null) {
            log.error("Unable to delete. Customer with id {} not found.", id);
            return new ResponseEntity<>(Customer,
                    HttpStatus.NOT_FOUND);
        }
        Customer = customerService.deleteCustomer(Customer);
        return new ResponseEntity<Customer>(Customer,HttpStatus.OK);
    }



}
