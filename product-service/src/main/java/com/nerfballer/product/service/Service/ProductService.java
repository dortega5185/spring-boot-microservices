package com.nerfballer.product.service.Service;

import java.util.List;
import java.util.Optional;

import com.nerfballer.product.service.model.Product;
import com.nerfballer.product.service.Repository.ProductRepository;
import com.nerfballer.product.service.dto.ProductRequest;
import com.nerfballer.product.service.dto.ProductResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	
//	public ProductService(ProductRepository productRepository) {
//		this.productReposity = productRepository;
//	}
	
	// void is used when you don't need to return
	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();
		
		productRepository.save(product);
		log.info("Product {} is saved", product.getId());
	}
	
	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();
		
//		products.stream().map(product -> mapToProductResponse(product));
		return products.stream().map(this::mapToProductResponse).toList();
	}
	
	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}
	
	public Optional<Product> getProductById(String productId) {
		return productRepository.findById(productId);
	}
	
	public Product updateProduct(String productId, Product updatedProduct) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found with id " + productId));
		
		product.setName(updatedProduct.getName());
		product.setDescription(updatedProduct.getDescription());
		product.setPrice(updatedProduct.getPrice());
		
		return productRepository.save(product);
	}
	
}
