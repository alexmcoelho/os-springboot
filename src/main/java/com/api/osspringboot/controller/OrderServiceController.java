package com.api.osspringboot.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.osspringboot.domain.model.OrderService;
import com.api.osspringboot.domain.service.OrderServiceService;
import com.api.osspringboot.model.OrderServiceInput;
import com.api.osspringboot.model.OrderServiceModel;
import com.api.osspringboot.repository.OrderServiceRepository;

@RestController
@RequestMapping("/order-service")
public class OrderServiceController {

	@Autowired
	private OrderServiceService orderServiceService;
	
	@Autowired
	private OrderServiceRepository orderServiceRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderServiceModel save(@Valid @RequestBody OrderServiceInput obj) {
		OrderService entity = toEntity(obj);
		return toModel(orderServiceService.save(entity));
	}
	
	@GetMapping()
	public List<OrderServiceModel> list() {
		return toCollectionModel(orderServiceRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderServiceModel> findById(@PathVariable Long id) {
		Optional<OrderService> obj = orderServiceRepository.findById(id);
		if(obj.isPresent()) {
			OrderServiceModel model = toModel(obj.get());
			return ResponseEntity.ok(model);
		}
		return ResponseEntity.notFound().build();
	}
	
	private OrderServiceModel toModel(OrderService obj) {
		return modelMapper.map(obj, OrderServiceModel.class);
	}
	
	private List<OrderServiceModel> toCollectionModel(List<OrderService> list) {
		return list.stream()
				.map(obj -> toModel(obj))
				.collect(Collectors.toList());
	}
	
	private OrderService toEntity(OrderServiceInput obj) {
		return modelMapper.map(obj, OrderService.class);
	}
}
