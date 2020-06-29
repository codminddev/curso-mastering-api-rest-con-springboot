package com.codmind.orderapi.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Product {
	private Long id;
	private String name;
	private String category;
}
