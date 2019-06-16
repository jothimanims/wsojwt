package com.test.demo.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Timer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.model.Employee;

@RestController
public class TimerEmployeeController {

	
	Timer timer;
	@RequestMapping("/accounts")
    public List<Employee> getAccount(HttpServletRequest request) 
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
	@RequestMapping("/accounts/{id}")
    public String getEmployees(@PathVariable("id")String id) 
    {
		Timer timer = new Timer();
		TimerEx timerEx = new TimerEx(id);
		timer.schedule(timerEx, 15000);
			return "test";
			
    }

	void test(){
		System.out.println("sechdule");
	}
}
