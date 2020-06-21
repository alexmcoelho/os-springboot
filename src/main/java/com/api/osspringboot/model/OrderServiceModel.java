package com.api.osspringboot.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.api.osspringboot.domain.model.StatusOrderService;

public class OrderServiceModel {
	
	private Long id;	
	private CustomerAbstractModel customer;
	private String description;
	private BigDecimal price;
	private StatusOrderService status;
	private OffsetDateTime dateOpen;
	private OffsetDateTime dateClose;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public StatusOrderService getStatus() {
		return status;
	}
	public void setStatus(StatusOrderService status) {
		this.status = status;
	}
	public OffsetDateTime getDateOpen() {
		return dateOpen;
	}
	public void setDateOpen(OffsetDateTime dateOpen) {
		this.dateOpen = dateOpen;
	}
	public OffsetDateTime getDateClose() {
		return dateClose;
	}
	public void setDateClose(OffsetDateTime dateClose) {
		this.dateClose = dateClose;
	}
	public CustomerAbstractModel getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerAbstractModel customer) {
		this.customer = customer;
	}
	
}
