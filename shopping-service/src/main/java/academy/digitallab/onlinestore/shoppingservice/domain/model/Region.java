package academy.digitallab.onlinestore.shoppingservice.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data

public class Region implements Serializable {

	private Long id;
	private String name;



}
