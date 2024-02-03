package com.nerfballer.inventoryservice;

import com.nerfballer.inventoryservice.repository.InventoryRepository;
import com.nerfballer.inventoryservice.model.Inventory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

    @Bean
    CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(100);
			
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone_13_red");
			inventory1.setQuantity(0);
			
			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("pixel_2");
			inventory2.setQuantity(50);
			
			Inventory inventory3 = new Inventory();
			inventory3.setSkuCode("pixel_3");
			inventory3.setQuantity(150);
			
			Inventory inventory4 = new Inventory();
			inventory4.setSkuCode("pixel_4");
			inventory4.setQuantity(50);
			
			Inventory inventory5 = new Inventory();
			inventory5.setSkuCode("pixel_5");
			inventory5.setQuantity(0);
			
			Inventory inventory6 = new Inventory();
			inventory6.setSkuCode("iphone_14");
			inventory6.setQuantity(80);
			
			Inventory inventory7 = new Inventory();
			inventory7.setSkuCode("iphone_14_black");
			inventory7.setQuantity(5);
			
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
			inventoryRepository.save(inventory3);
			inventoryRepository.save(inventory4);
			inventoryRepository.save(inventory5);
			inventoryRepository.save(inventory6);
			inventoryRepository.save(inventory7);
		};
	}
}
