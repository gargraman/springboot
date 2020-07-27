package com.sample.app.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.app.crud.model.Employee;
import com.sample.app.crud.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	// display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return viewPage(1, "firstName", "asc", model);
	}

	@GetMapping("/showAddEmployee")
	public String showAddEmployee(Model model) {
		Employee e = new Employee();
		model.addAttribute("employee", e);
		return "new_employee";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee emp) {
		this.service.saveEmployee(emp);
		return "redirect:/";
	}

	@GetMapping("/showUpdateEmployee/{id}")
	public String showUpdateEmployee(@PathVariable(value = "id") long id, Model model) {
		Employee e = this.service.getEmployeeById(id);
		model.addAttribute("employee", e);
		return "update_employee";
	}//

	@GetMapping("/showDeleteEmployee/{id}")
	public String showDeleteEmployee(@PathVariable(value = "id") long id, Model model) {
		this.service.deleteEmployee(id);
		return "redirect:/";
	}

	// display list of employees /page/1?sortby&order=asc
	@GetMapping("/page/{page}")
	public String viewPage(@PathVariable(value = "page") int page, @RequestParam("sortby") String sortby,
			@RequestParam("order") String order, Model model) {

		int size = 5;

		Page<Employee> p = service.findAll(page, size, sortby, order);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", p.getTotalPages());
		model.addAttribute("totalItems", p.getTotalElements());

		model.addAttribute("sortby", sortby);
		model.addAttribute("order", order);
		model.addAttribute("reverseorder", order.equalsIgnoreCase("asc") ? "desc" : "asc");

		model.addAttribute("listEmployees", p.getContent());
		return "index";
	}
}
