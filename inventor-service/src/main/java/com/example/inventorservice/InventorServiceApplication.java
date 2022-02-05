package com.example.inventorservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SpringBootApplication
public class InventorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepo, RepositoryRestConfiguration RestResource){
		RestResource.exposeIdsFor(Product.class);
		return args -> {
			productRepo.save(new Product(null,"ordinateur",2388,12));
			productRepo.save(new Product(null,"telephone",7288,22));
			productRepo.save(new Product(null,"imprimante",7888,42));
			productRepo.findAll().forEach(p->{
				System.out.println(p.getName());
			});
		};
	}


}


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
class Product{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	private double quantity;
}

@RepositoryRestResource
interface ProductRepository extends JpaRepository<Product,Long>{

}
