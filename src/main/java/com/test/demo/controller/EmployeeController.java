package com.test.demo.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.model.Employee;

@RestController
public class EmployeeController {
	
	@RequestMapping("/employe")
    public List<Employee> getEmployees(HttpServletRequest request) 
    {
		Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            System.out.println(key+ "  :  "+value);
        }

		List<Employee> employeesList = new ArrayList<Employee>();
		employeesList.add(new Employee(1,"test","test","test@gmail.com"));
		return employeesList;
    }
	@RequestMapping("/employe/{id}")
    public String getEmployees() 
    {
			return "test";
    }

}
