package com.nerfballer.product.service.Controller;

import java.util.List;
import java.util.Optional;

import com.nerfballer.product.service.Service.ProductService;
import com.nerfballer.product.service.dto.ProductRequest;
import com.nerfballer.product.service.dto.ProductResponse;
import com.nerfballer.product.service.model.Product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
	
	private final ProductService productService;
	// Post
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest productRequest) {
		productService.createProduct(productRequest);
	}
	
	// Get
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Product> getProductById(@PathVariable String id) {
		return productService.getProductById(id);
	}
	
	// Put
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}
}
