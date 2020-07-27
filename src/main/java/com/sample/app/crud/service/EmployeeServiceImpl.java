package com.sample.app.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sample.app.crud.model.Employee;
import com.sample.app.crud.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {

		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(Employee e) {
		this.employeeRepository.save(e);
		
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = this.employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}else {
			throw new RuntimeException("Employee not found for id:"+id);
		}
		
	}

	@Override
	public void deleteEmployee(long id) {
		Optional<Employee> employee = this.employeeRepository.findById(id);
		if(employee.isPresent()) {
			this.employeeRepository.deleteById(id);
		}else {
			throw new RuntimeException("Employee not found for id:"+id);
		}
	}

	@Override
	public Page<Employee> findAll(int pageNo, int size, String sort_field, String order) {
		Sort sort = order.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sort_field).ascending():Sort.by(sort_field).descending();
		
		Pageable pg = PageRequest.of(pageNo-1, size, sort);
		return this.employeeRepository.findAll(pg);
		
	}

}
