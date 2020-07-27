package com.sample.app.crud.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sample.app.crud.model.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees(); 
	void saveEmployee(Employee e);
	Employee getEmployeeById(long id);
	void deleteEmployee(long id);
	Page<Employee> findAll(int pageNo, int size, String sort_field, String order);

}
