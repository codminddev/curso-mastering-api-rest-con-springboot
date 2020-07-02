package com.codmind.orderapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codmind.orderapi.entity.Product;
import com.codmind.orderapi.repository.ProductRepositiry;
import com.codmind.orderapi.validators.ProductValidator;

@Service
public class ProductService {

	@Autowired
	private ProductRepositiry productRepo;
	
	
	public Product findById(Long productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("No existe el producto"));
		return product;
	}

	@Transactional
	public void delete(Long productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("No existe el producto"));
		productRepo.delete(product);
	}
	
	public List<Product> findAll(Pageable page){
		List<Product> products = productRepo.findAll(page).toList();
		return products;
	}
	
	@Transactional
	public Product save(Product product) {
		ProductValidator.save(product);
		
		if(product.getId() == null) {
			Product newProduct = productRepo.save(product);
			return newProduct;
		}
		
		Product exitProduct = productRepo.findById(product.getId())
				.orElseThrow(() -> new RuntimeException("No existe el producto"));
		
		exitProduct.setName(product.getName());
		exitProduct.setPrice(product.getPrice());
		
		productRepo.save(exitProduct);
		
		return exitProduct;
	}
}
