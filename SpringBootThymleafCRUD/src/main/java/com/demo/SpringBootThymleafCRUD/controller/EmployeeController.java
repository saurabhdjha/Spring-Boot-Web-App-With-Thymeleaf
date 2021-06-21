package com.demo.SpringBootThymleafCRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.SpringBootThymleafCRUD.model.Employee;
import com.demo.SpringBootThymleafCRUD.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String viewHomePage(Model model)
	{
		/*
		// we have retrieved list of employees and added it to model
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		// returned the thymleaf template whose name is index
		return "index";	*/
		
		return findPaginatedData(1,"employeeId","asc",model);
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
	
	// example of request: page/1/sortField=name&sortDir=asc
	@GetMapping("/page/{pageNo}")
	public String findPaginatedData(@PathVariable Integer pageNo, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir, Model model)
	{
		int pageSize=5; // how many rows displayed on 1 page, It can be dynamic but we have fixed it.
		Page<Employee> page=employeeService.findPaginatedData(pageNo, pageSize,sortField,sortDir);
		
		List<Employee> listEmployees=page.getContent();
		
		model.addAttribute("listEmployees", listEmployees);
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		// to toggle the change
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		return "index";
	}
}
