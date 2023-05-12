package com.web.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.model.Employee;
import com.web.service.EmployeeService;


@RestController
//@RequestMapping("/book")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@PostMapping("/save")
	public Employee save(@RequestBody Employee ed)
	{
		return service.save(ed);
	}
	
	@GetMapping("/findAll")
	public List<Employee> findAllEmps()
	{
		return service.findAll();
	}
	
	@GetMapping("findOne/{id}")
	public Employee findOne(@PathVariable Integer id)
	{
		return service.getOne(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id)
	{
		service.delete(id);
	}
	
	@PutMapping("/update/{id}")
	public Employee update(@RequestBody Employee ed,@PathVariable Integer id)
	{
		return service.updateEmp(ed, id);
	}
}
