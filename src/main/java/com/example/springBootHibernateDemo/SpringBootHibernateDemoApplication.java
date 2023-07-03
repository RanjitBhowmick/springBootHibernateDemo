package com.example.springBootHibernateDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.springBootHibernateDemo.entities.User;
import com.example.springBootHibernateDemo.services.CustomUserDetailService;

@SpringBootApplication
public class SpringBootHibernateDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootHibernateDemoApplication.class, args);
		
		CustomUserDetailService userDetailsService = context.getBean(CustomUserDetailService.class);
	       
        User user = new User();
        user.setUsername("Abhijit");
        user.setPassword("Abhijit123");
        user.setEnabled(true);

        userDetailsService.saveUser(user);

        System.out.println("User created successfully!");
	}

}
