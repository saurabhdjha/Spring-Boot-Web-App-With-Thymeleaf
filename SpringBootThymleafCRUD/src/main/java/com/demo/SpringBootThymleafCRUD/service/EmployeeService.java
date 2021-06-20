package com.demo.SpringBootThymleafCRUD.service;

import java.util.List;

import com.demo.SpringBootThymleafCRUD.model.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployees();
	public void addEmployee(Employee employee); 
	public void deleteEmployee(Integer employeeId);
	public Employee getEmployeeById(Integer employeeId);
}
