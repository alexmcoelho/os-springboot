package com.api.osspringboot.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.api.osspringboot.domain.ValidationGroups;
import com.api.osspringboot.domain.exception.BusinessException;
import com.api.osspringboot.model.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class OrderService {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid 
	@ConvertGroup(from = Default.class, to = ValidationGroups.CustomerId.class)
	@NotNull
	@ManyToOne
	private Customer customer;
	
	@NotBlank
	private String description;
	
	@NotNull
	private BigDecimal price;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private StatusOrderService status;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dateOpen;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime dateClose;
	
	@OneToMany(mappedBy = "orderService")
	private List<Comment> comments = new ArrayList<Comment>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderService other = (OrderService) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public boolean cannotBeCompleted() {
		return !StatusOrderService.ABERTA.equals(this.status);
	}
	
	public void close() { 
		if(cannotBeCompleted()) {
			throw new BusinessException("Ordem de serviço não pode ser finalizada.");
		}
		this.status = StatusOrderService.FINALIZADA;
		this.dateClose = OffsetDateTime.now();
	}

}
