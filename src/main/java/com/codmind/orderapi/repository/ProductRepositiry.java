package com.codmind.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codmind.orderapi.entity.Product;

@Repository
public interface ProductRepositiry extends JpaRepository<Product, Long>{

}
