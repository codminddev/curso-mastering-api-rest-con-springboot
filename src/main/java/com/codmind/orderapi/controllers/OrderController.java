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

import com.codmind.orderapi.converters.OrderConverter;
import com.codmind.orderapi.dtos.OrderDTO;
import com.codmind.orderapi.entity.Order;
import com.codmind.orderapi.services.OrderService;
import com.codmind.orderapi.utils.WrapperResponse;

@RestController
public class OrderController {
	
	@Autowired
	private OrderConverter converter;
	
	@Autowired
	private OrderService orderService;

	@GetMapping(value="/orders")
	public ResponseEntity<WrapperResponse<List<OrderDTO>>> findAll(
			@RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize
			){
		
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Order> orders = orderService.findAll(page);
		return new WrapperResponse<>(true, "success", converter.fromEntity(orders))
				.createResponse();
	}
	
	@GetMapping(value = "/orders/{id}")
	public ResponseEntity<WrapperResponse<OrderDTO>> findById(@PathVariable(name="id") Long id){
		Order order = orderService.findById(id);
		return new WrapperResponse<>(true, "success", converter.fromEntity(order))
				.createResponse();
	}
	
	@PostMapping(value="/orders")
	public ResponseEntity<WrapperResponse<OrderDTO>> create(@RequestBody OrderDTO order){
		Order newOrder = orderService.save(converter.fromDTO(order));
		return new WrapperResponse<>(true, "success", converter.fromEntity(newOrder))
				.createResponse();
	}
	
	@PutMapping(value="/orders")
	public ResponseEntity<WrapperResponse<OrderDTO>> update(@RequestBody OrderDTO order){
		Order newOrder = orderService.save(converter.fromDTO(order));
		return new WrapperResponse<>(true, "success", converter.fromEntity(newOrder))
				.createResponse();
	}
	
	@DeleteMapping(value="/orders/{id}")
	public ResponseEntity<?> delete(@PathVariable(name="id") Long id){
		orderService.delete(id);
		return new WrapperResponse<>(true, "success", null).createResponse();
	}
}
