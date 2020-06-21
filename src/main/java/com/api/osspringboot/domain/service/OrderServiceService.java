package com.api.osspringboot.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.osspringboot.domain.exception.BusinessException;
import com.api.osspringboot.domain.exception.EntityNotFoundException;
import com.api.osspringboot.domain.model.Customer;
import com.api.osspringboot.domain.model.OrderService;
import com.api.osspringboot.domain.model.StatusOrderService;
import com.api.osspringboot.model.Comment;
import com.api.osspringboot.repository.CommentRepository;
import com.api.osspringboot.repository.CustomerRepository;
import com.api.osspringboot.repository.OrderServiceRepository;

@Service
public class OrderServiceService {
	
	@Autowired
	private OrderServiceRepository orderServiceRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	public OrderService save(OrderService obj) {
		Customer customer = customerRepository.findById(obj.getCustomer().getId())
				.orElseThrow(() -> new BusinessException("Cliente não encontrado."));
		
		obj.setCustomer(customer);
		obj.setStatus(StatusOrderService.ABERTA);
		obj.setDateOpen(OffsetDateTime.now());
		return orderServiceRepository.save(obj);
	}
	
	public void closeOrder(Long orderServiceId) {
		OrderService orderService = find(orderServiceId);
		orderService.close();
		
		orderServiceRepository.save(orderService);
	}
	
	public Comment saveComment(Long orderServiceId, String description) {
		OrderService orderService = find(orderServiceId);
		Comment entity = new Comment();
		entity.setDateSend(OffsetDateTime.now());
		entity.setDescription(description);
		entity.setOrderService(orderService);
		
		return commentRepository.save(entity);
	}
	
	private OrderService find(Long orderServiceId) {
		return orderServiceRepository.findById(orderServiceId)
				.orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada."));
	}

}
