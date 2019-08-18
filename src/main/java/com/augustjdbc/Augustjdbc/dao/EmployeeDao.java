package com.augustjdbc.Augustjdbc.dao;

import com.augustjdbc.Augustjdbc.model.Employee;
import com.augustjdbc.Augustjdbc.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}                                                               //in application.properties file the employee is the database
                                                        //name of we are using in the mysql and which is having our table of employee