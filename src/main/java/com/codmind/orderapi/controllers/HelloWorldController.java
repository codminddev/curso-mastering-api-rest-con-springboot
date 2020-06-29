package com.codmind.orderapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codmind.orderapi.entity.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloWorldController {
	
	
	@GetMapping(value="hello")
	public String hello() {
		return "Hello world";
	}
}