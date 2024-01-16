package com.nerfballer.product.service.Service;

import java.util.List;

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
}
