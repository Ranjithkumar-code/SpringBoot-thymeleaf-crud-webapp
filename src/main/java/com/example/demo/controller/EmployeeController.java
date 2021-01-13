package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String GetAllEmployees(Model model) {
		model.addAttribute("listEmployee",employeeService.getAllEmployees());
		return "index";
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		Employee employee= new Employee();
		model.addAttribute("employee", employee);
		return "newEmployeeForm";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee,Model model) {
		//save employee to the database
		String msg= employeeService.saveEmployee(employee);
		model.addAttribute("massage", msg);
		model.addAttribute("msg_type", "warnning");
		if(msg.equals("Save Employee Successfully")) {
			return "redirect:/";
		}
		else {
			return "newEmployeeForm";
		}
	}
	
	@GetMapping("/showUpdateEmployeeForm/{id}")
	public String showUpdateEmployeeForm(@PathVariable(value="id") long id,Model model) {
		Employee employee=employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "updateEmployeeForm";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
}
