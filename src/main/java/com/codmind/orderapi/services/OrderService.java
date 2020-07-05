package com.codmind.orderapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codmind.orderapi.entity.Order;
import com.codmind.orderapi.exceptions.GeneralServiceException;
import com.codmind.orderapi.exceptions.NoDataFoundException;
import com.codmind.orderapi.exceptions.ValidateServiceException;
import com.codmind.orderapi.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepo;
	
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
}
