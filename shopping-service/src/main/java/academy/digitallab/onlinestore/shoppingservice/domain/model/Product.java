package academy.digitallab.onlinestore.shoppingservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


import java.io.Serializable;


@Data
public class Product implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Double stock;
    private Double  price;
    private String status;
    private Category category;



}
