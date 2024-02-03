package com.nerfballer.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nerfballer.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
