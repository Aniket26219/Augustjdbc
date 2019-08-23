package com.augustjdbc.Augustjdbc.controller;

import com.augustjdbc.Augustjdbc.dao.EmployeeDao;
import com.augustjdbc.Augustjdbc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping(value = "/getemp")
    public List<Employee> getEmployees(){
        List<Employee> list=employeeDao.getEmpList();       //for getting all recrods from database
        return list;
    }
    @GetMapping(value = "/getemp/{name}")
    public Employee getEmp(@PathVariable String name){
        Employee employee=employeeDao.getEmp(name);         // for getting a specific record from database
        return employee;
    }
    @PostMapping(value = "/savedata")
    public String saveData(@RequestBody Employee employee){
        String str=employeeDao.insertData(employee);        // for inserting data into database
        return str;
    }
    @PutMapping(value = "/updatedata/{id}/{name}")
    public String updateData(@PathVariable String name,@PathVariable int id){
        String str=employeeDao.updateData(id,name);     //for updating the existing data of database
        return str;
    }
}
