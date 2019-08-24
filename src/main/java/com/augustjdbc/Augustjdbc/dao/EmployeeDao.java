package com.augustjdbc.Augustjdbc.dao;

import com.augustjdbc.Augustjdbc.model.Department;
import com.augustjdbc.Augustjdbc.model.Employee;
import com.augustjdbc.Augustjdbc.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Map<String, Object>> getCominedData() {
        List<Map<String, Object>> list=jdbcTemplate.queryForList("select a.name, a.city,b.dept from employee a,department b where a.dept_id=b.id");
        return list;
    }

    @Override
    public String insertCombinedData(Employee employee) {
        Department dept=jdbcTemplate.queryForObject("select * from department where name=? or id=?",
                new Object[]{employee.getDepartment().getName(),employee.getDepartment().getId()},new BeanPropertyRowMapper<>(Department.class));
        if(dept==null){
            jdbcTemplate.update("insert into department values(?,?)",
                    new Object[]{employee.department.getId(),employee.department.getName()});
            jdbcTemplate.update("insert into employee values(?,?,?,?)",
                    new Object[]{employee.getId(),employee.getName(),employee.getCity(),employee.getDepartment().getId()});
            return "Data inserted";
        }
        jdbcTemplate.update("insert into employee values(?,?,?,?)",
                new Object[]{employee.getId(),employee.getName(),employee.getCity(),employee.getDepartment().getId()});
        return "Data inserted";
    }
}
// in insertCombinedData method firstly we are taking the department name and checking whether it is present in the table or not by using the select query
// and if condition and if present then it will return the department name which is not a null and if it is null then the department name and id will be
// inserted in the table and if it is not null only the employee data will be inserted