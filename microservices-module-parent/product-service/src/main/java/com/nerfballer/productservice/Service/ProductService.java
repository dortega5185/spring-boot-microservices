package com.nerfballer.productservice.Service;

import java.util.List;
import java.util.Optional;

import com.nerfballer.productservice.model.Product;
import com.nerfballer.productservice.Repository.ProductRepository;
import com.nerfballer.productservice.dto.ProductRequest;
import com.nerfballer.productservice.dto.ProductResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;
	@Autowired
	private MongoTemplate mongoTemplate;
	
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
	
	public List<Product> getProductByName(String productName) {
		return productRepository.findByName(productName);
	}
	
	public List<Product> findProductsByName(String partialName) {
		Criteria criteria = Criteria.where("name").regex(partialName, "i");
		Query query = new Query(criteria);
		return mongoTemplate.find(query, Product.class);
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
	
	public void deleteProduct(String productId) {
		productRepository.deleteById(productId);
	}
	
}
