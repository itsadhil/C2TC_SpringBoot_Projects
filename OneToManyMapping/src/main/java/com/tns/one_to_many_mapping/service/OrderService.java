package com.tns.one_to_many_mapping.service;

import com.tns.one_to_many_mapping.entity.Order;
import com.tns.one_to_many_mapping.exception.OrderNotFoundException; // Import the custom exception
import java.util.List;

public interface OrderService {

    // Save a new order
    Order saveOrder(Order order);

    // Get all orders
    List<Order> getAllOrders();

    // Get order by ID
    Order getOrderById(Long id) throws OrderNotFoundException; // Declare the exception

    // Update order by ID
    Order updateOrder(Long id, Order updatedOrder) throws OrderNotFoundException; // Declare the exception

    // Delete order by ID
    boolean deleteOrder(Long id) throws OrderNotFoundException; // Declare the exception
}
