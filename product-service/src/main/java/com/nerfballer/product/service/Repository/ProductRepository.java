package com.nerfballer.product.service.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerfballer.product.service.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	
	List<Product> findByName(String name);
}