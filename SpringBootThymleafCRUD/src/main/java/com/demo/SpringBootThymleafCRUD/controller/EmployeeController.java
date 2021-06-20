package com.demo.SpringBootThymleafCRUD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.SpringBootThymleafCRUD.model.Employee;
import com.demo.SpringBootThymleafCRUD.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String viewHomePage(Model model)
	{
		// we have retrieved list of employees and added it to model
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		// returned the thymleaf template whose name is index
		return "index";	
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model)
	{
		// creating model attribute to bind form data
		// creating an empty Emloyee object
		Employee employee=new Employee();
		// thymleaf will access this empty Employee Object for binding form data
		model.addAttribute("employee", employee);
		// returned the thymleaf template whose name is new_employee 
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee)
	{
		// save employee to database
		employeeService.addEmployee(employee);
		return "redirect:/";
	}
	
	@GetMapping("/showUpdateForm/{employeeId}")
	public String showUpdateForm(@PathVariable Integer employeeId, Model model)
	{
		Employee employee=employeeService.getEmployeeById(employeeId);
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{employeeId}")
	public String deleteEmployee(@PathVariable Integer employeeId)
	{
		employeeService.deleteEmployee(employeeId);
		return "redirect:/";
	}
}
