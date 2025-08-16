package com.tns.one_to_many_mapping.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tns.one_to_many_mapping.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
