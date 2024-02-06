package com.nerfballer.inventoryservice.controller;

import org.springframework.web.bind.annotation.*;

import com.nerfballer.inventoryservice.dto.InventoryResponse;
import com.nerfballer.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
	
	private final InventoryService inventoryService;
	
//	@GetMapping
//	@ResponseStatus(HttpStatus.OK)
//	public List<InventoryService>getAllInventory() {
//		return inventoryService.getAllInStock()
//	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
		return inventoryService.isInStock(skuCode);
	}
}
