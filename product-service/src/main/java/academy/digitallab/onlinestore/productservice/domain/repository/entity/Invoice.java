package academy.digitallab.onlinestore.productservice.domain.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "tlb_invoices")
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numberInvoice;
    private String description;

   // @JsonIgnoreProperties(value={"tlb_invoices", "hibernateLazyInitializer", "handler"}, allowSetters=true)
   // @ManyToOne(fetch = FetchType.LAZY)
   // private Customer customer;

    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private List<InvoiceItems> items;

}
