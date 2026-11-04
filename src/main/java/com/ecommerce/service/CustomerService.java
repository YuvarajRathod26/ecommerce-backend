package com.ecommerce.service;

import com.ecommerce.controller.CustomerController;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Customer;
import com.ecommerce.exception.CustomerNotFoundException;
import com.ecommerce.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository crepo;

	public Customer saveCustomer(Customer customer) {
		return crepo.save(customer);
	}

	public List<Customer> findAllCustomers() {
		return crepo.findAll();
	}

	public Customer findCustomerById(Long id) {

		Optional<Customer> optionalCustomer = crepo.findById(id);
		if (optionalCustomer.isEmpty()) {
			throw new CustomerNotFoundException("Customer not found with id: " + id);
		}

		return optionalCustomer.get();
	}

	public void deleteCustomer(Long id) {
		Optional<Customer> optionalCustomer = crepo.findById(id);

		if (optionalCustomer.isEmpty()) {
			throw new CustomerNotFoundException("Customer with id " + id + " not found");
		}

		crepo.deleteById(id);
	}

	public Customer updateCustomer(Long id, Customer customer) {

		Optional<Customer> optionalCustomer = crepo.findById(id);

		if (optionalCustomer.isEmpty()) {
			throw new CustomerNotFoundException("Customer with id " + id + " not found");
		}

		Customer existingCustomer = optionalCustomer.get();

		existingCustomer.setName(customer.getName());
		existingCustomer.setEmail(customer.getEmail());
		existingCustomer.setPassword(customer.getPassword());
		existingCustomer.setPhone(customer.getPhone());

		return crepo.save(existingCustomer);
	}
}