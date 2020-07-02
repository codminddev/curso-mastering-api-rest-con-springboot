package com.codmind.orderapi.validators;

import javax.management.RuntimeErrorException;

import com.codmind.orderapi.entity.Product;

public class ProductValidator {

	public static void save(Product product) {
		
		if(product.getName() == null || product.getName().trim().isEmpty()) {
			throw new RuntimeException("El nombre es requerido");
		}
		
		if(product.getName().length() > 100) {
			throw new RuntimeException("El nombre es muy largo (max 100)");
		}
		
		if(product.getPrice() == null) {
			throw new RuntimeException("El precio es requerido");
		}
		
		if(product.getPrice() < 0) {
			throw new RuntimeException("El precio es incorrecto");
		}
		
	}
}
