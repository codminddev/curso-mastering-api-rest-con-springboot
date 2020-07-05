package com.codmind.orderapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codmind.orderapi.converters.ProductConverter;
import com.codmind.orderapi.dtos.ProductDTO;
import com.codmind.orderapi.entity.Product;
import com.codmind.orderapi.services.ProductService;
import com.codmind.orderapi.utils.WrapperResponse;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductConverter converter;

	@GetMapping(value="/products/{productId}")
	public ResponseEntity<WrapperResponse<ProductDTO>> findById(@PathVariable("productId") Long productId) {
		Product product = productService.findById(productId);
		ProductDTO productDTO = converter.fromEntity(product);
		return new WrapperResponse<ProductDTO>(true, "success", productDTO)
				.createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping(value="/products/{productId}")
	public ResponseEntity<?> delete(@PathVariable("productId") Long productId) {
		productService.delete(productId);
		return new WrapperResponse(true, "success", null)
				.createResponse(HttpStatus.OK);
	}
	
	// /products?pageNumber=1&pageSize=10
	@GetMapping(value="/products")
	public ResponseEntity<List<ProductDTO>> findAll(
			@RequestParam(value="pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value="pageSize", required = false, defaultValue = "5") int pageSize
			){
		
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Product> products = productService.findAll(page);
		List<ProductDTO> dtoProducts = converter.fromEntity(products);
		
		return new WrapperResponse(true, "success", dtoProducts)
				.createResponse(HttpStatus.OK);
	}
	
	@PostMapping(value="/products") 
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO product) {
		Product newProduct = productService.save(converter.fromDTO(product));
		ProductDTO productDTO = converter.fromEntity(newProduct);
		
		return new WrapperResponse(true, "success", productDTO)
				.createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping(value="/products") 
	public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO product) {
		Product updateProduct = productService.save(converter.fromDTO(product));
		ProductDTO productDTO = converter.fromEntity(updateProduct);
		
		return new WrapperResponse(true, "success", productDTO)
				.createResponse(HttpStatus.OK);
	}
}
