package com.demo.SpringBootThymleafCRUD.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.SpringBootThymleafCRUD.model.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();
	public void addEmployee(Employee employee); 
	public void deleteEmployee(Integer employeeId);
	public Employee getEmployeeById(Integer employeeId);
	Page<Employee> findPaginatedData(Integer pageNo,Integer pageSize, String sortKey, String sortOrder);
}
