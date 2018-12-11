package com.Anton.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Anton.entities.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select customer from Customer customer where customer.name =:name AND customer.email =:email")
    Customer getCustomerByNameAndEmail(@Param("name") String name, @Param("email") String email);

}
