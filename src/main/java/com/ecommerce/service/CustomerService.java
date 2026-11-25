package com.ecommerce.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.CustomerRequestDTO;
import com.ecommerce.dto.CustomerResponseDTO;
import com.ecommerce.entity.Customer;
import com.ecommerce.exception.CustomerNotFoundException;
import com.ecommerce.repository.CustomerRepository;

@Service
public class CustomerService {

	private final CustomerRepository crepo;

	public CustomerService(CustomerRepository crepo) {
		this.crepo = crepo;
	}

	public CustomerResponseDTO saveCustomer(CustomerRequestDTO requestDTO) {
	    Customer customer = convertToEntity(requestDTO);
	    Customer savedCustomer = crepo.save(customer);
	    return convertToDTO(savedCustomer);
	}

	public List<CustomerResponseDTO> findAllCustomers() {
		List<Customer> customers = crepo.findAll();
		return customers.stream().map(this::convertToDTO).toList();
	}

	public CustomerResponseDTO findCustomerById(Long id) {

		Customer customer = crepo.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));

		return convertToDTO(customer);
	}

	public void deleteCustomer(Long id) {

		Customer customer = crepo.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));

		crepo.delete(customer);
	}

	public Customer updateCustomer(Long id, Customer customer) {

		Customer existingCustomer = crepo.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));

		existingCustomer.setName(customer.getName());
		existingCustomer.setEmail(customer.getEmail());
		existingCustomer.setPassword(customer.getPassword());
		existingCustomer.setPhone(customer.getPhone());

		return crepo.save(existingCustomer);
	}

	private CustomerResponseDTO convertToDTO(Customer customer) {

		CustomerResponseDTO response = new CustomerResponseDTO();

		response.setId(customer.getId());
		response.setName(customer.getName());
		response.setEmail(customer.getEmail());
		response.setPhone(customer.getPhone());

		return response;
	}
	
	private Customer convertToEntity(CustomerRequestDTO requestDTO) {

	    Customer customer = new Customer();

	    customer.setName(requestDTO.getName());
	    customer.setEmail(requestDTO.getEmail());
	    customer.setPassword(requestDTO.getPassword());
	    customer.setPhone(requestDTO.getPhone());

	    return customer;
	}
}