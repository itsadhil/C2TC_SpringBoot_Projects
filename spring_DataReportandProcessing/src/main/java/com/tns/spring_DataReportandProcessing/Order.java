package com.tns.spring_DataReportandProcessing;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private double amount;
    
    // only either id or ordernumber , customer who gave the order(mapping), on which date order should placed
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNumber=" + orderNumber + ", amount=" + amount + "]";
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(Long id, String orderNumber, double amount) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.amount = amount;
	}

   
}
