package com.codmind.orderapi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codmind.orderapi.entity.Order;
import com.codmind.orderapi.entity.OrderLine;
import com.codmind.orderapi.entity.Product;
import com.codmind.orderapi.entity.User;
import com.codmind.orderapi.exceptions.GeneralServiceException;
import com.codmind.orderapi.exceptions.NoDataFoundException;
import com.codmind.orderapi.exceptions.ValidateServiceException;
import com.codmind.orderapi.repository.OrderLineRepository;
import com.codmind.orderapi.repository.OrderRepository;
import com.codmind.orderapi.repository.ProductRepositiry;
import com.codmind.orderapi.security.UserPrincipal;
import com.codmind.orderapi.validators.OrderValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderLineRepository orderLineRepo;
	
	@Autowired
	private ProductRepositiry productRepo;
	
	public List<Order> findAll(Pageable page){
		try {
			return orderRepo.findAll(page).toList();
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	public Order findById(Long id) {
		try {
			return orderRepo.findById(id)
					.orElseThrow(() -> new NoDataFoundException("La orden no existe"));
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	public void delete(Long id) {
		try {
			Order order = orderRepo.findById(id)
					.orElseThrow(() -> new NoDataFoundException("La orden no existe"));
			
			orderRepo.delete(order);
			
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	@Transactional
	public Order save(Order order) {
		try {
			OrderValidator.save(order);
			
			User user = UserPrincipal.getCurrentUser();
			
			double total = 0;
			for(OrderLine line : order.getLines()) {
				Product product = productRepo.findById(line.getProduct().getId())
					.orElseThrow(() -> new NoDataFoundException("No existe el producto " + line.getProduct().getId()));
				
				line.setPrice(product.getPrice());
				line.setTotal(product.getPrice() * line.getQuantity());
				total += line.getTotal();
			}
			order.setTotal(total);
			
			order.getLines().forEach(line -> line.setOrder(order));
			
			if(order.getId() == null) {
				order.setUser(user);
				order.setRegDate(LocalDateTime.now());
				return orderRepo.save(order);
			}
			
			Order savedOrder = orderRepo.findById(order.getId())
					.orElseThrow(() -> new NoDataFoundException("La orden no existe"));
			order.setRegDate(savedOrder.getRegDate());
			
			List<OrderLine> deletedLines = savedOrder.getLines();
			deletedLines.removeAll(order.getLines());
			orderLineRepo.deleteAll(deletedLines);
			
			return orderRepo.save(order);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
}
