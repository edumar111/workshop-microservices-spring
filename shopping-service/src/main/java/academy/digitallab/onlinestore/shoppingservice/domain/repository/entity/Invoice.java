package academy.digitallab.onlinestore.shoppingservice.domain.repository.entity;

import academy.digitallab.onlinestore.shoppingservice.domain.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tlb_invoices")
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_invoice")
    private String numberInvoice;
    private String description;
    @Column(name = "customer_id")
    private Long customerId;
    @Transient
    private Customer customer;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;


    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItems> items;

    private String state;

    public Invoice(){
        items = new ArrayList<>();
    }

    @PrePersist
    public void prePersist() {

        this.createAt = new Date();
    }

}
