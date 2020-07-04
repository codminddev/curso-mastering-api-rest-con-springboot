package com.codmind.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codmind.orderapi.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
