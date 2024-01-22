package com.nerfballer.product.service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nerfballer.product.service.Repository.ProductRepository;
import com.nerfballer.product.service.model.Product;

import jakarta.annotation.PostConstruct;

@Service
public class FileProcessorService {
	
	@Autowired
	private ProductRepository productRepository;
	
	// use postconstruct method in @Service class to read and parse a file when the app runs
	@PostConstruct
	public void init() {
		String filePath = "/data.txt";
		try {
			List<Product> products = readAndParseFile(filePath);
			productRepository.saveAll(products);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		// saves to mongodb
	}
	
	private List<Product> readAndParseFile(String filePath) {
		List<Product> products = new ArrayList<>();
		try (InputStream inputStream = getClass().getResourceAsStream(filePath);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			products = reader.lines().map(this::parseLine).collect(Collectors.toList());
		} catch (IOException | NullPointerException e) {
			e.printStackTrace();
		}
		return products;
	}
			
	private Product parseLine(String line) {
			String[] data = line.split("\\|");
			System.out.println(Arrays.toString(data));
			Product product = new Product();
			product.setId(data[0].trim());
			product.setName(data[1].trim());
			product.setDescription(data[2].trim());
			product.setPrice(new BigDecimal(data[3].trim()));
			return product;			
	};
}
