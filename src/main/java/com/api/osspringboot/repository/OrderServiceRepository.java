package com.api.osspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.osspringboot.domain.model.OrderService;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {

}
