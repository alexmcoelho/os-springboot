package com.api.osspringboot.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.osspringboot.domain.model.Customer;
import com.api.osspringboot.domain.service.CustomerService;
import com.api.osspringboot.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;
		
	@GetMapping
	public List<Customer> listar() {
		return customerRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> findById(@PathVariable Long id) {
		Optional<Customer> obj = customerRepository.findById(id);
		if(obj.isPresent()) {
			return ResponseEntity.ok(obj.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer add(@Valid @RequestBody Customer obj) {
		return customerService.save(obj);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@Valid @PathVariable Long id, @RequestBody Customer obj) {
		if(!customerRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		obj.setId(id);
		obj = customerService.save(obj);
		return ResponseEntity.ok(obj); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if(!customerRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		customerService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
