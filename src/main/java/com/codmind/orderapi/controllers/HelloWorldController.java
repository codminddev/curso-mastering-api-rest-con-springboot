package com.codmind.orderapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codmind.orderapi.entity.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloWorldController {
	
	
	@GetMapping(value="hello")
	public String hello() {
		return "Hello world";
	}
	
	@GetMapping(value="products")
	public Product findProduct() {
		/*
		Product product = new Product();
		product.setId(1L);
		product.setName("Product 1");
		product.setCategory("category 1");
		*/
		
		Product product = Product.builder()
				.id(1L)
				.name("Product 1")
				.category("Category 1")
				.build();
		
		return product;
	}
}