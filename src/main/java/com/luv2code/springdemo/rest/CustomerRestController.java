package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	//autowired the CustomerService
	@Autowired
	CustomerService customerService ;//injecting dependency
	
	//add mapping for GET/customers
	@GetMapping("/customers")
	public List<Customer> getCustomers()
	{
		return customerService.getCustomers();
	}
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId )
	{ 
		Customer theCustomer = customerService.getCustomer(customerId);
		if(theCustomer==null)
			throw new CustomerNotFoundException("Customer not found-"+customerId);
		return theCustomer;
	} 
	//add mapping for POST/customers - add new customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer)
	{
        //set customerId 0 because hibernate will not create new customer
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer); 
		return theCustomer;
	}
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer)
	{
		customerService.saveCustomer(theCustomer); 
		return theCustomer;
	}
	
}
