package com.codmind.orderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codmind.orderapi.entity.OrderLine;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Long>{

}
