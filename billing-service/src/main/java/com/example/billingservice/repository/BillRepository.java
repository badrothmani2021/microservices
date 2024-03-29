package com.example.billingservice.repository;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.entities.Productitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface BillRepository extends JpaRepository<Bill,Long> {


}
