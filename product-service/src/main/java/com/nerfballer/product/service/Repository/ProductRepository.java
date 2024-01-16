package com.nerfballer.product.service.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nerfballer.product.service.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
