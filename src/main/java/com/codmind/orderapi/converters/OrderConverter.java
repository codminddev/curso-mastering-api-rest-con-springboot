package com.codmind.orderapi.converters;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.codmind.orderapi.dtos.OrderDTO;
import com.codmind.orderapi.dtos.OrderLineDTO;
import com.codmind.orderapi.entity.Order;
import com.codmind.orderapi.entity.OrderLine;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderConverter extends AbstractConverter<Order, OrderDTO> {

	private DateTimeFormatter dateTimeFormat;
	private ProductConverter productConverter;
	private UserConverter userConverter;

	@Override
	public OrderDTO fromEntity(Order entity) {
		if (entity == null)
			return null;

		List<OrderLineDTO> lines = fromOrderLineEntity(entity.getLines());

		return OrderDTO.builder()
				.id(entity.getId())
				.lines(lines)
				.regDate(entity.getRegDate().format(dateTimeFormat))
				.total(entity.getTotal())
				.user(userConverter.fromEntity(entity.getUser()))
				.build();
	}

	@Override
	public Order fromDTO(OrderDTO dto) {
		if (dto == null) return null;

		List<OrderLine> lines = fromOrderLineDTO(dto.getLines());

		return Order.builder()
				.id(dto.getId())
				.lines(lines)
				.total(dto.getTotal())
				.user(userConverter.fromDTO(dto.getUser()))
				.build();
	}
	
	private List<OrderLineDTO> fromOrderLineEntity(List<OrderLine> lines) {
		if(lines == null) return null;
		
		return lines.stream().map(line -> {
			return OrderLineDTO.builder()
					.id(line.getId())
					.price(line.getPrice())
					.product(productConverter.fromEntity(line.getProduct()))
					.quantity(line.getQuantity())
					.total(line.getTotal())
					.build();
		})
		.collect(Collectors.toList());
	}
	
	private List<OrderLine> fromOrderLineDTO(List<OrderLineDTO> lines) {
		if(lines == null) return null;
		
		return lines.stream().map(line -> {
			return OrderLine.builder()
					.id(line.getId())
					.price(line.getPrice())
					.product(productConverter.fromDTO(line.getProduct()))
					.quantity(line.getQuantity())
					.total(line.getTotal())
					.build();
		})
		.collect(Collectors.toList());
	}

}
