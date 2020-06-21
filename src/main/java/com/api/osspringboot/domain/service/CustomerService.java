package com.api.osspringboot.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.osspringboot.domain.exception.BusinessException;
import com.api.osspringboot.domain.model.Customer;
import com.api.osspringboot.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer save(Customer obj) {
		Customer customer = customerRepository.findByEmail(obj.getEmail());
		if(customer != null && !customer.equals(obj)) {
			throw new BusinessException("Já existe um cliente cadastrado com este e-mail");
		}
		return customerRepository.save(obj);
	}
	
	public void delete(Long id) {
		customerRepository.deleteById(id);
	}

}
