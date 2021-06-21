package com.demo.SpringBootThymleafCRUD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.SpringBootThymleafCRUD.model.Employee;
import com.demo.SpringBootThymleafCRUD.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}

	@Override
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Integer employeeId) {
		Employee employee=getEmployeeById(employeeId);
		employeeRepository.delete(employee);
	}

	@Override
	public Employee getEmployeeById(Integer employeeId) {
		Optional<Employee> employeeOpt=employeeRepository.findById(employeeId);
		if(employeeOpt.isEmpty())
			throw new RuntimeException(" Employee not found for id :: "+employeeId);
		Employee employee=employeeOpt.get();
		return employee;
	}

	@Override
	public Page<Employee> findPaginatedData(Integer pageNo, Integer pageSize, String sortKey, String sortOrder) {
		
		Sort sort=sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortKey).ascending() : Sort.by(sortKey).descending();
		Pageable pageable=PageRequest.of(pageNo-1, pageSize, sort);		
		return this.employeeRepository.findAll(pageable);
	}

	
}
