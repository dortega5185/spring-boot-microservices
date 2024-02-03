package com.nerfballer.productservice.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerfballer.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	
	List<Product> findByName(String name);
}
