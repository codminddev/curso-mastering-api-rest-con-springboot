package com.codmind.orderapi.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codmind.orderapi.entity.Product;

@RestController
public class ProductController {

	private List<Product> products = new ArrayList<>();
	
	public ProductController() {
		for(int c = 0; c< 10; c++) {
			products.add(Product.builder()
					.id((c+1L))
					.name("Product " + (c+1L))
					.build()
					);
		}
	}
	
	
	
	@GetMapping(value="/products/{productId}")
	public ResponseEntity<Product> findById(@PathVariable("productId") Long productId) {
		for(Product prod : this.products) {
			if(prod.getId().longValue() == productId.longValue()) {
				return new ResponseEntity<Product>(prod, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(value="/products/{productId}")
	public ResponseEntity<Void> delete(@PathVariable("productId") Long productId) {
		Product deleteProduct = null;
		for(Product prod : this.products) {
			if(prod.getId().longValue() == productId.longValue()) {
				deleteProduct = prod;
				break;
			}
		}
		
		if(deleteProduct == null) throw new RuntimeException("No existe el productos"); 
		
		var a = Product.builder().build();
		this.products.remove(deleteProduct);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping(value="/products")
	public ResponseEntity<List<Product>> findAll(){
		return new ResponseEntity<List<Product>>(this.products, HttpStatus.OK);
	}
	
	@PostMapping(value="/products") 
	public ResponseEntity<Product> create(@RequestBody Product product) {
		this.products.add(product);
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/products") 
	public ResponseEntity<Product> update(@RequestBody Product product) {
		for(Product prod : this.products) {
			if(prod.getId().longValue() == product.getId().longValue()) {
				prod.setName(product.getName());
				return new ResponseEntity<Product>(product, HttpStatus.OK);
			}
		}
		throw new RuntimeException("No existe el productos");
	}
	
}
