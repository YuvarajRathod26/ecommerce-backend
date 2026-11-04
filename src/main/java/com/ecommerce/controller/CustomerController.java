package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Customer;
import com.ecommerce.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService cservice;

	@PostMapping
	public Customer saveCustomer(@Valid @RequestBody Customer customer) {

		return cservice.saveCustomer(customer);
	}

	@GetMapping
	public List<Customer> findAllCustomers() {
		return cservice.findAllCustomers();
	}

	@GetMapping("/{id}")
	public Customer findCustomerById(@PathVariable Long id) {
		return cservice.findCustomerById(id);

	}

	@DeleteMapping("/{id}")
	public String deleteCustomer(@PathVariable Long id) {

		cservice.deleteCustomer(id);

		return "Customer deleted successfully";

	}

	@PutMapping("/{id}")
	public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {

		return cservice.updateCustomer(id, customer);
	}

}
