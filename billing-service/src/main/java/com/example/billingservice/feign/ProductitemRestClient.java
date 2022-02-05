package com.example.billingservice.feign;


import com.example.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductitemRestClient {

    @GetMapping(path = "/products")
    PagedModel<Product> pageProduct();
    @GetMapping(path = "/products/{id}")
    Product getProductById(@PathVariable Long id);

}
