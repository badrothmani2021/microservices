package com.example.customerservice;

import com.example.customerservice.entities.Customer;
import com.example.customerservice.repositort.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration){
		restConfiguration.exposeIdsFor(Customer.class);
		return args -> {

			customerRepository.save(new Customer(null,"mohamed","med@gmail.com"));
			customerRepository.save(new Customer(null,"badr eddine","badr@gmail.com"));
			customerRepository.save(new Customer(null,"aithammou","aithammou@gmail.com"));
			customerRepository.findAll().forEach(c->{
				System.out.println(c.toString());
			});
		};
	}

}
