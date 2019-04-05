package academy.digitallab.onlinestore.shoppingservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data

public class Customer implements Serializable {


    private Long id;


    private String firstName;


    private String lastName;


    private String email;


    private String photoUrl;


    private Region region;

    private String state;
}
