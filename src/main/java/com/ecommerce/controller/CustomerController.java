package com.ecommerce.controller;

import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.CustomerRequestDTO;
import com.ecommerce.dto.CustomerResponseDTO;
import com.ecommerce.entity.Customer;
import com.ecommerce.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService cservice;
	public CustomerController(CustomerService cservice) {
	    this.cservice = cservice;
	}

	@PostMapping
	public CustomerResponseDTO saveCustomer(@Valid @RequestBody CustomerRequestDTO requestDTO) {
	    return cservice.saveCustomer(requestDTO);
	}

	@GetMapping
	public List<CustomerResponseDTO> findAllCustomers() {
		return cservice.findAllCustomers();
	}

	@GetMapping("/{id}")
	public CustomerResponseDTO getCustomerById(@PathVariable Long id) {
	    return cservice.findCustomerById(id);
	}

	@DeleteMapping("/{id}")
	public String deleteCustomer(@PathVariable Long id) {

		cservice.deleteCustomer(id);

		return "Customer deleted successfully";

	}

	@PutMapping("/{id}")
	public Customer updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customer) {

		return cservice.updateCustomer(id, customer);
	}

}
