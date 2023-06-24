package com.example.springBootHibernateDemo.endpoints;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootHibernateDemo.entities.Employee;
import com.example.springBootHibernateDemo.repositories.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@PostMapping("/")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

	@PutMapping("/{id}")
	public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		Employee employee = new Employee();
		if (employeeOptional.isPresent()) {
			employee = employeeOptional.get();
			employee.setName(employeeDetails.getName());
			employee.setDesignation(employeeDetails.getDesignation());
		}
		return employeeRepository.save(employee);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		if (employeeOptional.isPresent()) {
			Employee employee = employeeOptional.get();
			employeeRepository.delete(employee);
		}
	}
}
