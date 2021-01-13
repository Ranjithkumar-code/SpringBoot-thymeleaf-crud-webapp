package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository EmployeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		
		return EmployeeRepository.findAll();
	}

	@Override
	public String saveEmployee(Employee employee) {
		if(employee.getFirstname()!=null&&employee.getLastname()!=null&&!employee.getEmail().isEmpty()&&!employee.getMobnum().isEmpty()) {
		EmployeeRepository.save(employee);	
		return "Save Employee Successfully";
		}
		else {
			return "Please fill all the fields"; 
		}
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional= EmployeeRepository.findById(id);
		Employee employee=null;
		if(optional.isPresent()) {
			return employee=optional.get();
		}
		else {
			throw new RuntimeException("Employee record not found this id ::"+id);
		}
	}

	@Override
	public void deleteEmployeeById(long id) {
		EmployeeRepository.deleteById(id);
	}

}
