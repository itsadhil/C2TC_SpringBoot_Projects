package com.tns.one_to_many_mapping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tns.one_to_many_mapping.entity.Customer;
import com.tns.one_to_many_mapping.entity.Order;
import com.tns.one_to_many_mapping.service.CustomerServiceImpl;
import com.tns.one_to_many_mapping.service.OrderServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private CustomerServiceImpl customerService;

    // POST method to create an order for an existing customer
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        // Retrieve the customer based on the order's customer reference
        Customer customer = order.getCustomer();
        
        if (customer == null || customerService.getCustomerById(customer.getId()) == null) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
        // Associate the customer with the order and save it
        order.setCustomer(customer);
        orderService.saveOrder(order);

        return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
    }

    // GET method to retrieve all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // GET method to retrieve an order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    // PUT method to update an existing order
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        try {
            // Retrieve the customer associated with the order, if present
            Customer customer = updatedOrder.getCustomer();
            
            if (customer == null || customerService.getCustomerById(customer.getId()) == null) {
                return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
            }

            // Associate the customer with the order
            updatedOrder.setCustomer(customer);

            // Update the order
            orderService.updateOrder(id, updatedOrder);
            return new ResponseEntity<>("Order updated successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // DELETE method to remove an order
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
