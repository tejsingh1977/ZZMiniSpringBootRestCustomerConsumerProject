package com.ibm.ecms;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.ibm.ecms.dto.CustomerDTO;


@SpringBootApplication
public class ZzMiniSpringBootRestCustomerConsumerProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ZzMiniSpringBootRestCustomerConsumerProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		CustomerDTO customer = new CustomerDTO();
		
		customer.setName("Antonio");
		customer.setEmailId("antonio@gmail.com");
		customer.setDateOfBirth(LocalDate.of(2024, 1, 10));		
		//addCustomer(customer);
		
		Integer customerId = 1007;
		//getCustomerDetails(customerId);
		deleteCustomer(customerId);
		
		
	}
	
	
	public void addCustomer(CustomerDTO customer) {	
		String url = "http://localhost:8085/hdfcbank/customers";	
		RestTemplate restTemplate = new RestTemplate();	
		String response = restTemplate.postForObject(url, customer, String.class);	
		System.out.println(response);	
		System.out.println("Customer Added successfully" );
	}
	
	public void getCustomerDetails(Integer customerId) {		
		String url = "http://localhost:8085/hdfcbank/customers/{customerId}";
		RestTemplate restTemplate = new RestTemplate();
		CustomerDTO customerDTO = restTemplate.getForObject(url, CustomerDTO.class, customerId);
		System.out.println(customerDTO);
	}
	
	public void updateCustomer(CustomerDTO customerDTO) {
		String url = "http://localhost:8085/hdfcbank/customers/{customerId}";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(url,customerDTO,customerDTO.getCustomerId());
		System.out.println("Customer upated successfully");
		
	}
	
	public void deleteCustomer(Integer customerId) {
		String url = "http://localhost:8085/hdfcbank/customers/{customerId}";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url, customerId);
		System.out.println("Customer deleted successfully");
	}

	

}
