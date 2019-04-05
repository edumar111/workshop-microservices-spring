package academy.digitallab.onlinestore.productservice;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


import java.util.Arrays;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductServiceApplication  {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}


}
