package com.augustjdbc.Augustjdbc.dao;

import com.augustjdbc.Augustjdbc.model.Department;
import com.augustjdbc.Augustjdbc.model.Employee;
import com.augustjdbc.Augustjdbc.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
//import java.util.Map;

@Repository
public class EmployeeDao implements EmployeeRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Employee> getEmpList() {
        List<Employee> list=jdbcTemplate.query("select * from employees",
                new BeanPropertyRowMapper<>(Employee.class));        //here Employee.class is the class from the model pacakage
        return list;                                               //this class is for mapping the fields in this class should be same
    }                                                           //as present in the table of database
                                                                //in application.properties file the employee is the database
    @Override                                               //name of we are using in the mysql and which is having our table of employee
    public Employee getEmp(String name) {
        Employee emp=jdbcTemplate.queryForObject("select * from Employee where name = ?",new Object[]{name},new BeanPropertyRowMapper<>(Employee.class));
        return emp;             //in this we are getting only one object or we can say only one record
    }

    @Override
    public String insertData(Employee employee) {
        jdbcTemplate.update("insert into employee values(?,?,?)",new Object[]{employee.getId(),employee.getName(),employee.getCity()});
        return "Data Saved";        //in this we are inserting data into database
    }

    @Override
    public String updateData(Integer id, String name) {
        jdbcTemplate.update("update employee set name = ? where id = ?",new Object[]{name,id});
        return "data updated";      //in this we are updating the existing data
    }
}
