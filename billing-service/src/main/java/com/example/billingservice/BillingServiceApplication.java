package com.example.billingservice;


import com.example.billingservice.entities.Bill;
import com.example.billingservice.entities.Productitem;
import com.example.billingservice.feign.CustomerRestClient;
import com.example.billingservice.feign.ProductitemRestClient;
import com.example.billingservice.model.Customer;
import com.example.billingservice.model.Product;
import com.example.billingservice.repository.BillRepository;
import com.example.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductitemRestClient productitemRestClient){
        return args -> {
            Customer customer = customerRestClient.getCustomerById(1l);
            Bill bill1 = billRepository.save(new Bill(null,new Date(),null,customer.getId(),null));
            PagedModel<Product> productPagedModel = productitemRestClient.pageProduct();
            productPagedModel.forEach(p -> {
                Productitem productitem = new Productitem();
                productitem.setPrice(p.getPrice());
                productitem.setQuantity(1+new Random().nextInt(100));
                productitem.setBill(bill1);
                productitem.setProductID(p.getId());
                productItemRepository.save(productitem);
            });
        };
    }


    }




