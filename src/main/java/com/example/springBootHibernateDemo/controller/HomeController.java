package com.example.springBootHibernateDemo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootHibernateDemo.entities.Employee;

@RestController
public class HomeController {

	@GetMapping("/welcome")
	public String welcomePage() {
		return "You are currently in the welcome page.";
	}

}
