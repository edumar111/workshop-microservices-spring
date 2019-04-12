package academy.digitallab.onlinestore.shoppingservice.api;

import academy.digitallab.onlinestore.shoppingservice.domain.InvoiceService;
import academy.digitallab.onlinestore.shoppingservice.domain.repository.entity.Invoice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <h1>InvoiceRest<h1/>
 * This class Resource Invoice for Rest Api
 * @author Eduaro Marchena @edumar111
 * @version 1.0
 * @since 2019
 * **/
@Slf4j
@RestController
@RequestMapping("/invoices")
public class InvoiceRest {

    @Autowired
    InvoiceService invoiceService;

    // -------------------Retrieve All Invoices--------------------------------------------

    @GetMapping
    public ResponseEntity<List<Invoice>> listAllInvoices() {
        List<Invoice> Invoices = invoiceService.findInvoiceAll();
        if (Invoices.isEmpty()) {
            return new ResponseEntity<>(Invoices, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Invoices, HttpStatus.OK);
    }

    // -------------------Retrieve Single Invoice------------------------------------------

    @GetMapping(value = "/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable("id") long id) {
        log.info("Fetching Invoice with id {}", id);
        Invoice Invoice = invoiceService.getInvoice(id);
        if (Invoice == null) {
            log.error("Invoice with id {} not found.", id);
            return new ResponseEntity<>(Invoice, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Invoice, HttpStatus.OK);
    }

    // -------------------Create a Invoice-------------------------------------------

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice Invoice) {
        log.info("Creating Invoice : {}", Invoice);


        invoiceService.createInvoice (Invoice);

        return new ResponseEntity<>(Invoice, HttpStatus.CREATED);
    }

    // ------------------- Update a Invoice ------------------------------------------------

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable("id") long id, @RequestBody Invoice Invoice) {
        log.info("Updating Invoice with id {}", id);

        Invoice currentInvoice = invoiceService.getInvoice(id);

        if (currentInvoice == null) {
            log.error("Unable to update. Invoice with id {} not found.", id);
            return new ResponseEntity<>(currentInvoice,
                    HttpStatus.NOT_FOUND);
        }

        Invoice.setId(id);

        currentInvoice=invoiceService.updateInvoice(Invoice);
        return new ResponseEntity<>(currentInvoice, HttpStatus.OK);
    }

    // ------------------- Delete a Invoice-----------------------------------------

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Invoice with id {}", id);

        Invoice Invoice = invoiceService.getInvoice(id);
        if (Invoice == null) {
            log.error("Unable to delete. Invoice with id {} not found.", id);
            return new ResponseEntity<>(Invoice,
                    HttpStatus.NOT_FOUND);
        }
        Invoice = invoiceService.deleteInvoice(Invoice);
        return new ResponseEntity<Invoice>(Invoice,HttpStatus.OK);
    }

}
