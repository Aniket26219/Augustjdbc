package com.augustjdbc.Augustjdbc.repository;

import com.augustjdbc.Augustjdbc.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeRepo {
    List<Employee> getEmpList();            //Employee is the class name of model class
    Employee getEmp(String name);
    String insertData(Employee employee);
    String updateData(Integer id, String name);
    List<Map<String, Object>> getCominedData();
    String insertCombinedData(Employee employee);
}
