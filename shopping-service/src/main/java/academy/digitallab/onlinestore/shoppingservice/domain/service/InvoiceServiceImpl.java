package academy.digitallab.onlinestore.shoppingservice.domain.service;

import academy.digitallab.onlinestore.shoppingservice.domain.InvoiceService;
import academy.digitallab.onlinestore.shoppingservice.domain.client.CustomerClient;
import academy.digitallab.onlinestore.shoppingservice.domain.client.ProductClient;
import academy.digitallab.onlinestore.shoppingservice.domain.model.Customer;
import academy.digitallab.onlinestore.shoppingservice.domain.model.Product;
import academy.digitallab.onlinestore.shoppingservice.domain.repository.InvoiceItemsRepository;
import academy.digitallab.onlinestore.shoppingservice.domain.repository.InvoiceRepository;
import academy.digitallab.onlinestore.shoppingservice.domain.repository.entity.Invoice;
import academy.digitallab.onlinestore.shoppingservice.domain.repository.entity.InvoiceItems;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceItemsRepository invoiceItemsRepository;

    @Autowired
    ProductClient productClient;

    @Autowired
    CustomerClient customerClient;

    @Override
    public List<Invoice> findInvoiceAll() {
        return  invoiceRepository.findAll();
    }

    @Transactional
    @Override
    public Invoice createInvoice(Invoice invoice) {
        invoice.setState("CREATED");
        Invoice invoiceDB= invoiceRepository.save(invoice);
        for (InvoiceItems item: invoice.getItems() ){
            productClient.updateStockProduct ( item.getProductId (), item.getQuantity () );
        }

        return invoiceDB;
    }

    @Transactional
    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());
        return invoiceRepository.save(invoiceDB);
    }

    @Transactional
    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());
        if (invoiceDB == null){
            return  null;
        }
        invoiceDB.setState("DELETED");
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice getInvoice(Long id) {
        Invoice  invoice= invoiceRepository.findById(id).orElse(null);
        if (invoice != null){

            Customer customer= customerClient.getCustomer ( invoice.getCustomerId ()  ).getBody ();
            invoice.setCustomer ( customer );
            log.info ( customer.getLastName () );
            List<InvoiceItems> listTem= new ArrayList <> (  );
            for (InvoiceItems item: invoice.getItems() ){
                Long idProduct = item.getProductId ();
                Product product = productClient.getProduct ( idProduct ).getBody ();
                item.setProduct ( product );
                listTem.add ( item );
                log.info ( product.getName () );
            }
            invoice.setItems ( listTem );
        }
        return invoice ;
    }
}
