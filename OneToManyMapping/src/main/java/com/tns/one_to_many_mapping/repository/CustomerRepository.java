package com.tns.one_to_many_mapping.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tns.one_to_many_mapping.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
