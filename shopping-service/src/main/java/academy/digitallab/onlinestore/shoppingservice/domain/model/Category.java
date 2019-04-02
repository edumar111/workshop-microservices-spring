package academy.digitallab.onlinestore.shoppingservice.domain.model;

import lombok.Data;

import java.io.Serializable;

@Data

public class Category implements Serializable {

    private Long id;

    private String name;
}
