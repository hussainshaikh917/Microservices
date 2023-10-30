package com.seleniumexpress.employeeapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.seleniumexpress.employeeapp.entity.Employee;
import com.seleniumexpress.employeeapp.repo.EmployeeRepo;
import com.seleniumexpress.employeeapp.response.AddressResponse;
import com.seleniumexpress.employeeapp.response.EmployeeResponse;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	
	@Autowired(required = true)
	private ModelMapper modelMapper;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	
	// DB call -> employee 1
	public EmployeeResponse getEmployeeByID(int id) {
	
		Employee employee = employeeRepo.findById(id).get();
		
		// Employee -> EmployeeResponse
		
		
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		
//		AddressResponse addressResponse = new AddressResponse();
		
		
		 AddressResponse addressResponse = restTemplate.getForObject("http://localhost:8082/address-app/api/address/{id}", AddressResponse.class, id);
		
		 employeeResponse.setAddressResponse(addressResponse);
		 
		return employeeResponse;
		
	}
	
	
}
