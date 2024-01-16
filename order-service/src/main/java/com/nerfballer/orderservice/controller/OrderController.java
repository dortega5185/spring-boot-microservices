package com.nerfballer.orderservice.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.nerfballer.orderservice.dto.OrderRequest;
import com.nerfballer.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String placeOrder(@RequestBody OrderRequest orderRequest) {
		orderService.placeOrder(orderRequest);
		return "Order Placed Successfully";
	}
}
