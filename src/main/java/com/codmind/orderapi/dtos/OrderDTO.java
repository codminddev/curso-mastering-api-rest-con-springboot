package com.codmind.orderapi.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

	private Long id;
	private String regDate;
	private List<OrderLineDTO> lines;
	private Double total;
	private UserDTO user;
}
