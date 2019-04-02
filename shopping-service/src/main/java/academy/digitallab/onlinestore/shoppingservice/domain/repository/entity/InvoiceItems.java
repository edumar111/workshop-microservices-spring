package academy.digitallab.onlinestore.shoppingservice.domain.repository.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tbl_invoce_items")
public class InvoiceItems implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantity;
    private Double  price;

    @Column(name = "product_id")
    private Long productId;

}
