package com.example.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sample {

	@RequestMapping("/")
	public String verify(){
		return "Hello there!";
	}
}
